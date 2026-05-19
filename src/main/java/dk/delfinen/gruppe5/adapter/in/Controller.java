package dk.delfinen.gruppe5.adapter.in;

import dk.delfinen.gruppe5.application.port.in.*;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Discipline;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.model.TrainingResult;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

import java.util.ArrayList;
import java.util.Optional;

public class Controller {
    // UI / IO
    private final Presenter presenter;
    private final InputHandler inputHandler;

    // Infrastructure
    private final MemberRepository members;
    private final IdGenerator idGenerator;

    // Use cases
    private final AssignTrainerToCompetitiveSwimmerUseCase assignTrainer;
    private final DeleteMemberUseCase deleteMember;
    private final EditMemberUseCase editMember;
    private final FindMemberUseCase findMember;
    private final ListMembersUseCase listMembers;
    private final MarkMembershipPaymentUseCase markMembership;
    private final RegisterBestTrainingResultUseCase registerBestTrainingResult;
    private final RegisterCompetitionResultCase registerCompetitionResultCase;
    private final RegisterMemberUseCase registerMember;
    private final RegisterSwimmerDisciplinesUseCase registerSwimmerDisciplines;
    private final SortMembersUseCase sortMembers;
    private final ViewExpectedYearlyIncomeUseCase viewIncome;


    public Controller(
            Presenter presenter,
            InputHandler inputHandler,
            MemberRepository members,
            IdGenerator idGenerator,
            AssignTrainerToCompetitiveSwimmerUseCase assignTrainer,
            DeleteMemberUseCase deleteMember,
            EditMemberUseCase editMember,
            FindMemberUseCase findMember,
            ListMembersUseCase listMembers,
            MarkMembershipPaymentUseCase markMembership,
            RegisterBestTrainingResultUseCase registerBestTrainingResult,
            RegisterCompetitionResultCase registerCompetitionResultCase,
            RegisterMemberUseCase registerMember,
            RegisterSwimmerDisciplinesUseCase registerSwimmerDisciplines,
            SortMembersUseCase sortMembers,
            ViewExpectedYearlyIncomeUseCase viewIncome
    ) {
        this.presenter = presenter;
        this.inputHandler = inputHandler;
        this.members = members;
        this.idGenerator = idGenerator;
        this.assignTrainer = assignTrainer;
        this.deleteMember = deleteMember;
        this.editMember = editMember;
        this.findMember = findMember;
        this.listMembers = listMembers;
        this.markMembership = markMembership;
        this.registerBestTrainingResult = registerBestTrainingResult;
        this.registerCompetitionResultCase = registerCompetitionResultCase;
        this.registerMember = registerMember;
        this.registerSwimmerDisciplines = registerSwimmerDisciplines;
        this.sortMembers = sortMembers;
        this.viewIncome = viewIncome;
    }

    public void start() {
        inputHandler.chooseLooping(() -> "log ind som...", new MenuOption[]{
                new MenuOption("klubformand (se medlemmer)", () -> {
                    memberMenu();
                }),
                new MenuOption("kasserer", () -> {
                    treasurerMenu();
                }),
                new MenuOption("træner", () -> {
                    trainerMenu();
                }),
        });
    }

    private void memberMenu() {
        inputHandler.chooseLooping(
                () -> presenter.MemberList(),
                new MenuOption[]{
                        new MenuOption("tilføj medlem", () -> {
                            addMember();
                        }),
                        new MenuOption("slet medlem", () -> {
                            deleteMember();
                        }),
                        new MenuOption("rediger medlem", () -> {
                            editMember();
                        }),
                        new MenuOption("sorter efter aktivitet", () -> {
                            sortMembers.sortByActivity(members.findAll());
                        }),
                }
        );
    }

    public void addMember() {

        String name = inputHandler.getString("Indtast navnet på personen som skal tilføjes");
        int birthYear = inputHandler.getInt("Indtast fødselsåret på personen");
        Boolean isCompetitor = inputHandler.getBoolean("Er medlemmet konkurrerende?");
        String activity = inputHandler.getString("Hvilken aktivitet er han medlem af");
        Boolean isActive = inputHandler.getBoolean("Er medlemmet aktivt?");
        registerMember.execute(name, birthYear, isCompetitor, activity, isActive);
        System.out.println("Medlemmet er blevet oprettet");
    }

    public void deleteMember() {
        System.out.println(presenter.MemberList());
        deleteMember.execute(inputHandler.getInt("Indtast id nummer på det medlem du vil slette:"));
    }

    public void editMember() {

        Member memberToEdit = findMember.execute(
                        inputHandler.getInt("Indtast id'et på det medlem du vil redigere: "))
                .orElse(null);

        inputHandler.chooseLooping(
                () -> "Hvad vil du ændre?:",
                new MenuOption[]{
                        new MenuOption("Skift navn", () -> {
                            memberToEdit.setName(inputHandler.getString("Skriv navn"));
                        }),
                        new MenuOption("Skift fødselsår", () -> {
                            memberToEdit.setBirthYear(inputHandler.getInt("Nyt fødselsår: "));
                        }),
                        new MenuOption("Skift konkurrerende status", () -> {
                            memberToEdit.setIsCompetitor(inputHandler.getBoolean("Er medlemmet konkurrerende? (ja/nej)"));
                        }),
                        new MenuOption("Skift aktivitet", () -> {
                            memberToEdit.setActivity(inputHandler.getString("Skriv aktivitet"));
                        }),
                        new MenuOption("Skift medlemskab", () -> {
                            memberToEdit.setMembership(inputHandler.getBoolean("Er medlemmet aktivt?")
                                    ? new ActiveMembership()
                                    : new PassiveMembership());
                        }),
                }
        );
    }

    public void treasurerMenu() {
        inputHandler.chooseLooping(
                () -> presenter.SubscriptionPaidList(),
                new MenuOption[]{
                        new MenuOption("marker medlem som betalt", () -> {
                            boolean success = markMembership.execute(inputHandler.getInt(
                                    "skriv id på det medlem du vil markere som betalt"));
                            if (success) {
                                System.out.println("medlem betalt");
                            } else {
                                System.out.println("fejl, medlem kunne ikke findes");
                            }
                        }),
                        new MenuOption("se forventet årlig ", () -> {
                            System.out.println("Årlig forventet indkomst er: " + viewIncome.execute());
                        }),
                });
    }

    public void markMemberAsPaid() {
        int id = inputHandler.getInt("Indtast ID på medlem der har betalt: ");
        Optional<Member> memberOpt = members.find(id);

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setHasPaid(true);
            System.out.println("Medlem markeret som betalt: " + member.getName());
        } else {
            System.out.println("Intet medlem fundet med det ID.");
        }
    }

    public void trainerMenu() {
        inputHandler.chooseLooping(
                () -> "Træner Menu",  // eventuelt en liste over mine svømmere
                new MenuOption[]{
                        new MenuOption("overtag svømmere", () -> {
                            int id = inputHandler.getInt("Indtast id på svømmeren");
                            String name = inputHandler.getString("Indtast navn på træneren");

                            boolean success = assignTrainer.execute(id, name);

                            if (success) {
                                System.out.println("Træner " + name + " blev tildelt svømmeren.");
                            } else {
                                System.out.println("Kunne ikke finde medlemmet.");
                            }
                        }),

                        new MenuOption("registrer træningstider", () -> {
                            int id = inputHandler.getInt("Indtast id på svømmeren");
                            String discipline = inputHandler.getString("Indtast disciplin");
                            double time = inputHandler.getDouble("Indtast tid");

                            boolean success = registerBestTrainingResult.execute(id, time, discipline);

                            if (success) {
                                System.out.println("Resultat registreret");
                            } else {
                                System.out.println("Kunne ikke finde medlemmet");
                            }
                        }),

                        new MenuOption("registrer stævneresultat", () -> {
                            int id = inputHandler.getInt("Indtast id på svømmeren:");
                            Discipline discipline = Discipline.valueOf(inputHandler.getString("Indtast disciplin (Butterfly, Crawl, Rygcrawl, Brystsvømning):"));
                            int placement = inputHandler.getInt("Indtast placering:");
                            String competition = inputHandler.getString("Indtast stævnenavn:");
                            String date = inputHandler.getDate("Indtast dato:");


                            boolean success = registerCompetitionResultCase.execute(id, discipline, placement, competition, date);

                            if (success) {
                                System.out.println("Konkurrenceresultat registreret");
                            } else {
                                System.out.println("Kunne ikke finde medlemmet");
                            }

                        }),
                        new MenuOption("opdater svømmediscipliner", () -> {
                            int id = inputHandler.getInt("Indtast id på svømmeren:");
                            String discipline = inputHandler.getString("Indtast disciplin (Butterfly, Crawl, Rygcrawl, Brystsvømning):");
                            boolean success = registerSwimmerDisciplines.execute(id, discipline);

                            if (success) {
                                System.out.println("Disciplin registreret");
                            } else {
                                System.out.println("Kunne ikke finde medlemmet");
                            }
                        }),

                        new MenuOption("se statistik", () -> {
                            int id = inputHandler.getInt("indtast id på Svømmeren");
                            Optional<Member> memberOpt = members.find(id);
                            if (memberOpt.isPresent()) {
                                ArrayList<TrainingResult> results = memberOpt.get().getTrainingResults();
                                if (results.isEmpty()) {
                                    System.out.println("Ingen træningsresultater fundet");
                                } else {
                                    TrainingResult bedste = results.get(0);
                                    for (TrainingResult result : results) {
                                        if (result.getTime() < bedste.getTime()) {
                                            bedste = result;
                                        }
                                    }
                                    System.out.println("Bedste tid: " + bedste.getTime() + " i " + bedste.getDiscipline());
                                }
                            } else {
                                System.out.println("Intet medlem fundet med det ID.");
                            }
                        }),
                }
        );
    }
}