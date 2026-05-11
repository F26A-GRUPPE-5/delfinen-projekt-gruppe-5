package dk.delfinen.gruppe5.adapter.in;
// Adapter laget kaldes også infrastructure i Clean Architecture

import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.adapter.out.persistence.InMemoryMemberRepository;
import dk.delfinen.gruppe5.application.port.in.RegisterMemberUseCase;
import dk.delfinen.gruppe5.application.port.in.SortMembersUseCase;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.RandomIdGenerator;
import dk.delfinen.gruppe5.application.usecase.RegisterMemberUseCaseImpl;
import dk.delfinen.gruppe5.application.usecase.SortMembersUseCaseImpl;

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
    ControllerElementer controllerElementer;
    // MemberList members = new MemberList();
    InputHandler inputHandler;


    public Controller() {
        members = new InMemoryMemberRepository();
        presenter = new Presenter(members);
        idGenerator = new RandomIdGenerator();
        sortMembers = new SortMembersUseCaseImpl();
        registerMember = new RegisterMemberUseCaseImpl(idGenerator, members);
        controllerElementer = new ControllerElementer(idGenerator, members);
        inputHandler = new InputHandler();
    }





    public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        FileMemberSerializer serializer = new FileMemberSerializer();
        MemberRepository repository = new FileMemberRepository(serializer, "Members.csv");



        boolean running = true;

        while (running) {

            controllerElementer.printMenu();

            int inputMenuChoice = inputHandler.getInt(scanner, "Indtast et tal:");

            switch (inputMenuChoice) {

                //Alt relateret til oprettelse af medlemmer
                case 1:

                    controllerElementer.printMenuMember();

                    int inputMemberChoice = inputHandler.getInt(scanner, "Indtast et tal: ");

                    switch (inputMemberChoice) {

                        //Tilføj medlem
                        case 1:
                            controllerElementer.appAddMember(scanner);
                            break;

                        //Slet medlem
                        case 2:
                            controllerElementer.deleteMember(scanner);
                            break;

                        //Liste over medlemmer
                        case 3:

                            sortMembers.sortByActivity(members.findAll());
                            presenter.printMemberList();
                            break;

                        //Redigering af medlemmer
                        case 4:
                            controllerElementer.editMember(scanner);
                            break;

                        case 5:
                            break;

                    }
                    break;

                // Alt relateret til kontigent
                case 2:

                    controllerElementer.printMenuKontigent();

                    int inputSubscriptionChoice = inputHandler.getInt(scanner, "Indtast et tal: ");
                    switch (inputSubscriptionChoice) {
                    }

                    switch (inputSubscriptionChoice) {

                        // liste over status af betalte kontigenter
                        case 1:
                            presenter.printSubscriptionPaidList();
                            break;

                        // Marker members som har betalt
                        case 2:
                            controllerElementer.markMemberAsPaid(scanner);
                            break;

                        case 3:
                            break;

                    }
                    break;

                case 3:
                    repository.saveAll(members.findAll());
                    running = false;


            }

        }

    }
}


