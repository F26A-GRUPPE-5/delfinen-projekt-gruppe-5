package dk.delfinen.gruppe5.adapter.in;
// Adapter laget kaldes også infrastructure i Clean Architecture

import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;
import dk.delfinen.gruppe5.adapter.in.Presenter;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: Denne skal nok deles op i 2:
// 1. Router - Her defineres menu navigationen, hvilken text der skal stå, og der matches med en use case.
// 2. InputHandler - skal sørges for at hvis input er forkert type, gentages prompten til brugeren. Her kan også parses, altså gøre brugerinput læsbart
public class Controller {

    static MemberList members = new MemberList();
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        loadMembers();

        Boolean running = true;

        while (running) {
            System.out.println("--------------------------------------");
            System.out.println("Menu:");
            System.out.println("1. Tilføj et medlem");
            System.out.println("2. Slet et medlem");
            System.out.println("3. Se liste over medlemer");
            System.out.println("4. Exit");
            System.out.println("--------------------------------------");
            Scanner scanner = new Scanner(System.in);

            while (!scanner.hasNextInt()) {
                System.out.println("Indtast et tal: ");
                scanner.next();
            }
            int inputMenuChoice = scanner.nextInt();

            switch (inputMenuChoice) {

                case 1:
                    appAddMember(scanner, members);
                    break;

                case 2:
                    deleteMember(scanner, members.getMembers());
                    break;

                case 3:
                    members.sortByActivity();

                    presenter.printMemberlist();
                    break;

                case 4:
                    running = false;

            }



        }



    }

    public static void appAddMember(Scanner scanner, MemberList memberList) {
        scanner.nextLine();

        System.out.println("Indtast navnet på personen som skal tilføjes");
        System.out.println("Navnet:");
        String name = scanner.nextLine();

        System.out.println("Indtast fødselsåret på personen");
        System.out.println("Fødselsåret:");
        int birthYear = scanner.nextInt();

        System.out.println("Er medlemmet konkurrerende?");
        System.out.println("(ja / nej)");
        String isCompetitorAnswear = scanner.nextLine();
        boolean isCompetitor;
        if (isCompetitorAnswear.equals("ja")) {
            isCompetitor = true;
        } else {
            isCompetitor = false;
        }

        scanner.nextLine();

        System.out.println("Hvilken aktivitet er han medlem af");
        String activity = scanner.nextLine();

        System.out.println("Hvilken salgs medlemskab har personen?");
        System.out.println("(active/passive):");
        String type = scanner.nextLine();

        Membership membership;

        if (type.equalsIgnoreCase("active")) {
            membership = new ActiveMembership();
        } else {
            membership = new PassiveMembership();
        }

        members.addMember(name, birthYear, isCompetitor, activity, membership);

        System.out.println("Memberen er blevet oprettet: ");


    }

    public static void deleteMember(Scanner scanner, ArrayList<Member> members) {
        System.out.println("Indtast id nummer på det medlem du vil slette:");
        int chosenMember = scanner.nextInt();

        Member toRemove = null;

        for (Member member : members) {
            if (member.getMemberId() == chosenMember) {
                toRemove = member;
                break;
            }
        }

        if (toRemove != null) {
            members.remove(toRemove);
            System.out.println("Du har slettet: ");
            System.out.println();
            System.out.println(toRemove);
        } else {
            System.out.println("Intet medlem fundet med det Id.");
        }
    }

    public static void loadMembers() {
        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();

        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer",passive);
        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);
    }


}
