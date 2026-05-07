package dk.delfinen.gruppe5;


public class Main {
    public static void main(String[] args) {
        MemberList members = new MemberList();
        members.addMember(new Member("Jørgen", 1977, true, "konkurrencesvømmere", new ActiveMembership()));
        members.addMember(new Member("Hans Hansen", 2002, true, "konkurrencesvømmer", new ActiveMembership()));
        members.addMember(new Member("Gert Gertsen", 1920, true, "konkurrencesvømmer",new PassiveMembership()));
        members.addMember(new Member("Cay", 1975, true, "konkurrencesvømmer", new PassiveMembership()));
        members.sortByActivity();
        System.out.println(members);
    }
}
