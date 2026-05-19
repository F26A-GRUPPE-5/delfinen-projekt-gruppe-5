package dk.delfinen.gruppe5;


import dk.delfinen.gruppe5.adapter.in.Controller;
import dk.delfinen.gruppe5.adapter.in.InputHandler;
import dk.delfinen.gruppe5.adapter.in.Presenter;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.adapter.out.persistence.InMemoryMemberRepository;
import dk.delfinen.gruppe5.application.port.in.*;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.RandomIdGenerator;
import dk.delfinen.gruppe5.application.service.SystemClock;
import dk.delfinen.gruppe5.application.usecase.*;
import dk.delfinen.gruppe5.domain.model.*;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        MemberRepository memberRepository = new FileMemberRepository(new FileMemberSerializer(),
//                "src/main/java/data/Members.csv"

            // Infrastructure
            MemberRepository memberRepository = new InMemoryMemberRepository();
            IdGenerator idGen = new RandomIdGenerator();



            // Use cases
            AssignTrainerToCompetitiveSwimmerUseCase assignTrainer =
                    new AssignTrainerToCompetitiveSwimmerUseCaseImpl(memberRepository);

            DeleteMemberUseCase deleteMember =
                    new DeleteMemberUseCaseImpl(memberRepository);

            EditMemberUseCase editMember =
                    new EditMemberUseCaseImpl(memberRepository);

            FindMemberUseCase findMember =
                    new FindMemberUseCaseImpl(memberRepository);

            ListMembersUseCase listMembers =
                    new ListMembersUseCaseImpl(memberRepository);

            MarkMembershipPaymentUseCase markMembership =
                    new MarkMembershipPaymentUseCaseImpl(memberRepository);

            RegisterBestTrainingResultUseCase registerBestTrainingResult =
                    new RegisterBestTrainingResultUseCaseImpl(memberRepository);

            RegisterCompetitionResultCase registerCompetitionResultCase =
                    new RegisterCompetitionResultUseCaseImpl(memberRepository);

            RegisterMemberUseCase registerMember =
                    new RegisterMemberUseCaseImpl(idGen, memberRepository);

            RegisterSwimmerDisciplinesUseCase registerSwimmerDisciplines =
                    new RegisterSwimmerDisciplinesUseCaseImpl(memberRepository);

            SortMembersUseCase sortMembers =
                    new SortMembersUseCaseImpl();

            ViewExpectedYearlyIncomeUseCase viewIncome =
                    new ViewExpectedYearlyIncomeUseCaseImpl(
                            memberRepository,
                            new CalculateMembershipFeeUseCaseImpl(
                                    new SystemClock(), memberRepository));

        // UI helpers
        Presenter presenter = new Presenter(memberRepository, listMembers);
        InputHandler inputHandler = new InputHandler(new Scanner(System.in));

            // Controller (constructor order MUST match fields)
            Controller controller = new Controller(
                    presenter,
                    inputHandler,
                    memberRepository,
                    idGen,
                    assignTrainer,
                    deleteMember,
                    editMember,
                    findMember,
                    listMembers,
                    markMembership,
                    registerBestTrainingResult,
                    registerCompetitionResultCase,
                    registerMember,
                    registerSwimmerDisciplines,
                    sortMembers,
                    viewIncome
            );

            controller.start();

//
//        Membership active = new ActiveMembership();
//
//
//        // Opretter et Member objekt (senior)
//        Member member = new Member(
//                1,
//                "Magnus",
//                2008,
//                true,
//                "Konkurrencesvømmer",
//                active
//        );
//
//
//        Trainer trainer = new Trainer("Peter"); // Opretter en træner
//        Trainer trainer2 = new Trainer(""); // Tester tomt trænernavn
//        Trainer newTrainer = new Trainer("Michael"); // Ny træner
//
//        member.setTrainerName(trainer.getName());
//        System.out.println(member.getTrainerName() + " er træneren");
//
//        memberRepository.save(member); // Gemmer member i CSV-fil
//        Member loadedMember = memberRepository.find(1); // Henter member fra filen igen
//        System.out.println(
//                loadedMember.getTrainerName()
//                        + " blev hentet fra filen"
//        );
//
//
//        // Opretter en CompetitiveSwimmer
//        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(member, trainer);
//
//        // Ændrer træneren
//        CompetitiveSwimmer.addCoachToCompSwimmer(
//                swimmer,
//                newTrainer
//        );
//        System.out.println(swimmer.getTrainer());
//
//        ArrayList<CompetitiveSwimmer> swimmers = new ArrayList<>(); // Liste med swimmers
//        swimmers.add(swimmer); // Tilføjer swimmer til listen
//
//        swimmer.addDiscipline(Discipline.Crawl);
//        swimmer.addDiscipline(Discipline.Butterfly);
//
//        // Loop som viser alle swimmers
//        for (CompetitiveSwimmer s : swimmers) {
//
//            // Tjekker om svømmeren har discipliner
//            if (!s.getDisciplines().isEmpty()) {
//
//                System.out.println(
//                        s.getMember().getName()
//                                + " -> "
//                                + s.getTeam()
//                );
//
//            } else {
//
//                System.out.println(
//                        s.getMember().getName()
//                                + " har ingen discipliner"
//                );
//            }
//        }
//
//        System.out.println(swimmer.getTeam());
//        System.out.println(swimmer);
//

//        Result result1 = new Result(
//        MeetResult meetResult1 = new MeetResult(
//                Discipline.Crawl,
//                55.3, //svømmetiden
//                1, //placering i konkurrencen
//                "Delfin stævnet 2026", //Navn på stævnet
//                "12-05-2026"
//        );
//        swimmer.addResult(result1);
//
//        swimmer.addMeetResult(meetResult1);
//
//        System.out.println(swimmer);


//
//
//        Scanner scanner = new Scanner(System.in);
//
//        Membership active = new ActiveMembership();
//        Membership passive = new PassiveMembership();
//        SortMembersUseCaseImpl members = new SortMembersUseCaseImpl();
//
//
//        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
//        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
//        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer", passive);
//        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);
//
//
//        for (Member member : members.getMembers()) {
//            memberRepository.save(member);
//        }
//
//
//
//        System.out.println(
//                String.format(
//                        "the saved members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );
//        memberRepository.delete(members.getMembers().get(0).getId());
//
//        System.out.println(
//                String.format(
//                        "after deleting the saved members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );
//
//        Member newMember = new Member(999, "Ronald McDonald", 1940, true, "konkurrencesvømmere", active);
//
//        memberRepository.save(newMember);
//
//        System.out.println(
//                String.format(
//                        "after adding Ronald McDonald the members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );

//        members.sortByActivity();
//
//        System.out.println(members);
//
//
//        System.out.println("Id on the member you wanna se Fee on");
//        int input = scanner.nextInt();
//        System.out.printf("%.0f%n", members.getMemberById(input).getFee());
    }
}