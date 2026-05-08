package dk.delfinen.gruppe5;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();
        MemberList members = new MemberList();


        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
        Member jørgen = members.getMembers().get(0);
        jørgen.setHasPaid(false);
        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
        Member hans = members.getMembers().get(1);
        hans.setHasPaid(true);
        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer", passive);
        Member gert = members.getMembers().get(2);
        gert.setHasPaid(true);
        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);
        Member cay = members.getMembers().get(3);
        cay.setHasPaid(true);

        members.sortByActivity();

        System.out.println(members);


        System.out.println("Id on the member you wanna se Fee on");
        int input = scanner.nextInt();
        System.out.printf("%.0f%n", members.getMemberById(input).getFee());

        Subscription subscription = new Subscription(members);
        System.out.printf("Samlet kontingent %.0f kr.%n", subscription.totalSubscription());
        System.out.println("Har betalt");
        for (Member m : subscription.getPaidMembers()) {
            System.out.println(m);

        }
        System.out.println("Har ik betalt");
        for (Member m : subscription.getUnpaidMembers()) {
            System.out.println(m);

        }
    }
}

