package dk.delfinen.gruppe5.adapter.in;
// Adapter laget kaldes også infrastructure i Clean Architecture

import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.application.port.in.DeleteMemberUseCase;
import dk.delfinen.gruppe5.application.port.in.RegisterMemberUseCase;
import dk.delfinen.gruppe5.application.port.in.SortMembersUseCase;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.RandomIdGenerator;
import dk.delfinen.gruppe5.application.usecase.RegisterMemberUseCaseImpl;
import dk.delfinen.gruppe5.application.usecase.SortMembersUseCaseImpl;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

import java.util.Scanner;

//TODO: Denne skal nok deles op i 2:
// 1. Router - Her defineres menu navigationen, hvilken text der skal stå, og der matches med en use case.
// 2. InputHandler - skal sørges for at hvis input er forkert type, gentages prompten til brugeren. Her kan også parses, altså gøre brugerinput læsbart
public class Controller {
    MemberRepository members;
    Presenter presenter;
    RegisterMemberUseCase registerMember;
    SortMembersUseCase sortMembers;
    IdGenerator idGenerator;
    InputHandler inputHandler;
    DeleteMemberUseCase deleteMember;


    public Controller(
            MemberRepository members,
            RegisterMemberUseCase registerMember,
            SortMembersUseCase sortMembers,
            DeleteMemberUseCase deleteMember,
            IdGenerator idGenerator
    ) {
        this.members = members;
        this.presenter = new Presenter(members);
        this.registerMember = registerMember;
        this.sortMembers = sortMembers;
        this.deleteMember = deleteMember;
        this.idGenerator = idGenerator;
        this.inputHandler = new InputHandler(new Scanner(System.in));
    }

    public void start() {
        inputHandler.chooseLooping("log ind som...", new MenuOption[]{
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
                presenter.MemberList(),
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
        int id = inputHandler.getInt("Indtast id'et på det medlem du vil redigere: ");

        if (!members.exists(id)) {
            System.out.println("Intet medlem kunne finde med det id");
            return;
        }
        Member memberToEdit = members.find(id);

        inputHandler.chooseLooping(
                "Indtast nr på handling:",
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
                presenter.SubscriptionPaidList(),
                new MenuOption[]{
                        new MenuOption("marker medlem som betalt", () -> {
                            markMemberAsPaid();
                        }),
                        new MenuOption("se forventet årlig ", () -> {
                            markMemberAsPaid();
                        }),
                        new MenuOption("marker medlem som betalt", () -> {
                            markMemberAsPaid();
                        }),

                });
    }

    public void markMemberAsPaid() {
        Member member = members.find(inputHandler.getInt("Indtast ID på medlem der har betalt: "));
        if (member != null) {
            member.setHasPaid(true);
            System.out.println("Medlem markeret som betalt: " + member.getName());
        } else {
            System.out.println("Intet medlem fundet med det ID.");
        }
    }
    public void trainerMenu() {
//        inputHandler.chooseLooping(
//                "Træner Menu",  // eventuelt en liste over mine svømmere
//                new MenuOption[]{
//                        new MenuOption("overtag svømmere", () -> {
//                              ...
//                            AssignTrainerToCompetitiveSwimmer();
//                        }),
//                new MenuOption[]{
//                        new MenuOption("registrer træningsresultat", () -> {
//                            ...();
//                        }),
//                new MenuOption[]{
//                        new MenuOption("registrer stævneresultat", () -> {
//                            ...();
//                        }),
//                new MenuOption[]{
//                        new MenuOption("opdater svømmediscipliner", () -> {
//                            ...();
//                        }),
//                new MenuOption[]{
//                        new MenuOption("se statistik", () -> {
//                            ...();
//                        }),
//                });

    }
}


