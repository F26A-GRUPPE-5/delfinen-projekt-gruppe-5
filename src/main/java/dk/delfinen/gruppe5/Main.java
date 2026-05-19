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



        // Infrastructure
//        MemberRepository memberRepository = new InMemoryMemberRepository();
        MemberRepository memberRepository = new FileMemberRepository(new FileMemberSerializer(),
                "src/main/java/data/Members.csv");
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
    }
}