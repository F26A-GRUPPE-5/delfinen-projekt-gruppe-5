package dk.delfinen.gruppe5.adapter.in;

import dk.delfinen.gruppe5.InputHandler;
import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerElementer {
    static Presenter presenter = new Presenter();

    public static void printMenu() {
        System.out.println("--------------------------------------");
        System.out.println("Menu:");
        System.out.println("1. Medlemmer");
        System.out.println("2. Konkigent");
        System.out.println("3. Exit / Save");
        System.out.println("--------------------------------------");
    }

    public static void printMenuMember() {
        System.out.println("--------------------------------------");
        System.out.println("1. Tilføj et medlem");
        System.out.println("2. Slet et medlem");
        System.out.println("3. Se liste over medlemer");
        System.out.println("4. Ændre et medlem");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------");
    }

    public static void printMenuKontigent() {
        System.out.println("--------------------------------------");
        System.out.println("1. Se liste over kontigent statuser");
        System.out.println("2. Marker et medlem som betalt");
        System.out.println("3. Exit");
        System.out.println("--------------------------------------");
    }

    public static void appAddMember(Scanner scanner, MemberList members) {
        scanner.nextLine();

        System.out.println("Indtast navnet på personen som skal tilføjes");
        System.out.println("Navnet:");
        String name = scanner.nextLine();

        System.out.println("Indtast fødselsåret på personen");
        System.out.println("Fødselsåret:");
        int birthYear = InputHandler.getInt(scanner, "Fødselsåret: ");

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
        int chosenMember = InputHandler.getInt(scanner, "Indtast id nummer på det medlem fu vil slette:");

        Member toRemove = null;

        for (Member member : members) {
            if (member.getId() == chosenMember) {
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
            System.out.println("Intet medlem fundet med det Id. ");
        }
    }

    public static void editMember(Scanner scanner, MemberList members) {

        presenter.printMemberList();
        System.out.println("Indtast id'et på det medlem du vil redigere: ");
        int id = InputHandler.getInt(scanner, "Indtast id'et på det medlem du vil redigere: ");

        Member memberToEdit = members.getMemberById(id);

        if (memberToEdit == null) {
            System.out.println("Intet medlem kunne finde med det id");
            return;
        }

        boolean editMemberRunning = true;
        while(editMemberRunning) {

            System.out.println("--------------------------------------");
            System.out.println("1. Skift navn");
            System.out.println("2. Skift fødselsår");
            System.out.println("3. Skift konkurrerende status");
            System.out.println("4. Skift aktivitet");
            System.out.println("5. Skift medlemskab");
            System.out.println("6. Exit");

            System.out.println("--------------------------------------");
            System.out.println("Indtast nr på handling:  ");
            int inputEditChoice = InputHandler.getInt(scanner, "Indtast nr på handling: ");
            scanner.nextLine();

            switch (inputEditChoice) {

                // String name
                case 1:
                    System.out.println("Nyt navn:");
                    String name = scanner.nextLine();
                    memberToEdit.setName(name);
                    break;

                // int birthYear
                case 2:
                    System.out.println("Nyt fødselsår:");
                    int birthYear = InputHandler.getInt(scanner, "Nyt fødselsår: ");
                    scanner.nextLine();
                    memberToEdit.setBirthYear(birthYear);
                    break;

                // Boolean isCompetitor
                case 3:
                    System.out.println("Er medlem konkurrerende? (ja/nej):");
                    String competitorAns = scanner.nextLine();
                    memberToEdit.setIsCompetitor(competitorAns.equalsIgnoreCase("ja"));
                    break;

                // String activity
                case 4:
                    System.out.println("Ny aktivitet:");
                    String activity = scanner.nextLine();
                    memberToEdit.setActivity(activity);
                    break;

                // membership (active / passive)
                case 5:
                    System.out.println("Medlemskab (active/passive):");
                    String type = scanner.nextLine();

                    if (type.equalsIgnoreCase("active")) {
                        memberToEdit.setMembership(new ActiveMembership());
                    } else {
                        memberToEdit.setMembership(new PassiveMembership());
                    }
                    break;

                case 6:
                    editMemberRunning = false;
                    break;
            }




        }



    }

    public static void markMemberAsPaid(Scanner scanner, MemberList members) {
        System.out.println("Indtast ID på medlem der har betalt:");
        int id = InputHandler.getInt(scanner, "Indtast ID på medlem der har betalt: ");

        Member member = members.getMemberById(id);

        if (member != null) {
            member.setHasPaid(true);
            System.out.println("Medlem markeret som betalt: " + member.getName());
        } else {
            System.out.println("Intet medlem fundet med det ID.");
        }
    }

}
