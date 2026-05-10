package dk.delfinen.gruppe5;


import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MemberRepository memberRepository =
                new FileMemberRepository(
                        new FileMemberSerializer(),
                        "src/main/java/data/Members.csv"
                );
        memberRepository.clearAll();


        Scanner scanner = new Scanner(System.in);

        Membership active = new ActiveMembership();
        Membership passive = new PassiveMembership();
        MemberList members = new MemberList();


        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer", passive);
        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);


        for (Member member : members.getMembers()) {
            memberRepository.save(member);
        }



        System.out.println(
                String.format(
                        "the saved members are \n-------------\n:%s",
                        memberRepository.findAll()
                )
        );
        memberRepository.delete(members.getMembers().get(0).getId());

        System.out.println(
                String.format(
                        "after deleting the saved members are \n-------------\n:%s",
                        memberRepository.findAll()
                )
        );

        Member newMember = new Member(999, "Ronald McDonald", 1940, true, "konkurrencesvømmere", active);

        memberRepository.save(newMember);

        System.out.println(
                String.format(
                        "after adding Ronald McDonald the members are \n-------------\n:%s",
                        memberRepository.findAll()
                )
        );

//        members.sortByActivity();
//
//        System.out.println(members);
//
//
//        System.out.println("Id on the member you wanna se Fee on");
//        int input = scanner.nextInt();
//        System.out.printf("%.0f%n", members.getMemberById(input).getFee());
    }
}