package dk.delfinen.gruppe5;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();
        MemberList members = new MemberList();


        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer",passive);
        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);

        members.sortByActivity();

        System.out.println(members);


        System.out.println("Id on the member you wanna se Fee on");
        int input = scanner.nextInt();
        System.out.printf("%.0f%n", members.getMemberById(input).getFee());
    }
}
