package dk.delfinen.gruppe5;


import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;

public class Main {
    public static void main(String[] args) {
        MemberList members = new MemberList();
        members.addMember(new Member("Jørgen", 1977, true, false, "konkurrencesvømmer"));
        members.addMember(new Member("Hans Hansen", 2002, true, false, "motionist"));
        members.addMember(new Member("Gert Gertsen", 1920, true, true, "konkurrencesvømmer"));
        members.addMember(new Member("Cay", 1975, true, true, "konkurrencesvømmer"));
        members.sortByActivity();
        System.out.println(members);
    }
}
