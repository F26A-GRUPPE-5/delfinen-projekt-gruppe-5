package dk.delfinen.gruppe5.adapter.in;
// Adapter laget kaldes også infrastructure i Clean Architecture

import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.usecase.MemberList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//TODO: Denne skal nok deles op i 2:
// 1. Router - Her defineres menu navigationen, hvilken text der skal stå, og der matches med en use case.
// 2. InputHandler - skal sørges for at hvis input er forkert type, gentages prompten til brugeren. Her kan også parses, altså gøre brugerinput læsbart
public class Controller {



    static MemberList members = new MemberList();
    static Presenter presenter = new Presenter();



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        FileMemberSerializer serializer = new FileMemberSerializer();
        MemberRepository repository = new FileMemberRepository(serializer, "Members.csv");

        members.setMembers(repository.findAll());


        Boolean running = true;

        while (running) {

            ControllerElementer.printMenu();

            while (!scanner.hasNextInt()) {
                System.out.println("Indtast et tal: ");
                scanner.next();
            }
            int inputMenuChoice = scanner.nextInt();

            switch (inputMenuChoice) {

                //Alt relateret til oprettelse af medlemmer
                case 1:

                    ControllerElementer.printMenuMember();

                    while (!scanner.hasNextInt()) {
                        System.out.println("Indtast et tal: ");
                        scanner.next();
                    }
                    int inputMemberChoice = scanner.nextInt();

                    switch (inputMemberChoice) {

                        //Tilføj medlem
                        case 1:
                            ControllerElementer.appAddMember(scanner, members);
                            break;

                        //Slet medlem
                        case 2:
                            ControllerElementer.deleteMember(scanner, members.getMembers());
                            break;

                        //Liste over medlemmer
                        case 3:
                            members.sortByActivity();
                            presenter.printMemberList();
                            break;

                        //Redigering af medlemmer
                        case 4:
                            ControllerElementer.editMember(scanner, members);
                            break;

                        case 5:
                            break;

                    }
                    break;

                // Alt relateret til kontigent
                case 2:

                    ControllerElementer.printMenuKontigent();

                    while (!scanner.hasNextInt()) {
                        System.out.println("Indtast et tal: ");
                        scanner.next();
                    }
                    int inputSubscriptionChoice = scanner.nextInt();

                    switch(inputSubscriptionChoice) {

                        // liste over status af betalte kontigenter
                        case 1:
                            presenter.printSubscriptionPaidList();
                            break;

                        // Marker members som har betalt
                        case 2:
                            ControllerElementer.markMemberAsPaid(scanner, members);
                            break;

                        case 3:
                            break;

                    }
                    break;

                case 3:
                    repository.saveAll(members.getMembers());
                    running = false;

            }

        }

    }

}
