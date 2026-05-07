package dk.delfinen.gruppe5;

import java.util.Scanner;

public class App {
    private static MemberList members = new MemberList();
    public static void main(String[] args) {
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
                System.out.println("Indtast et tal:");
                scanner.next();
            }
            int inputMenuChoice = scanner.nextInt();

            switch (inputMenuChoice) {

                case 1:
                    appAddMember(scanner, members);
                    break;

                case 2:


                case 3:
                    members.sortByActivity();

                    System.out.println(members);

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

    public static void loadMembers() {
        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();

        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer",passive);
        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);
    }

}
