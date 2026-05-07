package dk.delfinen.gruppe5;


public class Main {
    public static void main(String[] args) {

        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();
        MemberList members = new MemberList();


        members.addMember(new Member("Jørgen", 1940, true, "konkurrencesvømmere", active, members.getMembers().size() + 1));
        members.addMember(new Member("Hans Hansen", 2022, true, "konkurrencesvømmer", active, members.getMembers().size() + 1));
        members.addMember(new Member("Gert Gertsen", 1920, true, "konkurrencesvømmer",passive, members.getMembers().size() + 1));
        members.addMember(new Member("Cay", 1975, true, "konkurrencesvømmer", active, members.getMembers().size() + 1));


        System.out.println(members);
        System.out.println(members.getMemberById(1).getFee());
    }
}
