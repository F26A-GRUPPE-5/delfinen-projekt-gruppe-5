package dk.delfinen.gruppe5;


import dk.delfinen.gruppe5.adapter.in.Controller;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberRepository;
import dk.delfinen.gruppe5.adapter.out.persistence.FileMemberSerializer;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.usecase.SortMembersUseCaseImpl;
import dk.delfinen.gruppe5.domain.model.*;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MemberRepository memberRepository =
                new FileMemberRepository(
                        new FileMemberSerializer(),
                        "src/main/java/data/Members.csv"
                );
        //memberRepository.clearAll(); Har sat den på hold da den sletter hele CSV filen hver gang programmet starter. Det er derfor ting kan forsvinde eller opføre sig mærkeligt.

        Controller controller = new Controller();
        controller.start();

        Membership active = new ActiveMembership();


        // Opretter et Member objekt (senior)
        Member member = new Member(
                1,
                "Magnus",
                2008,
                true,
                "Konkurrencesvømmer",
                active
        );



// Opretter en træner
        Trainer trainer = new Trainer("Peter");


// Opretter en CompetitiveSwimmer
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(member, trainer);

        swimmer.addDiscipline(Discipline.Crawl);
        swimmer.addDiscipline(Discipline.Butterfly);

        System.out.println(swimmer.getTeam());
        System.out.println(swimmer);

        Result result1 = new Result(
                Discipline.Crawl,
                55.3, //svømmetiden
                1, //placering i konkurrencen
                "Delfin stævnet 2026", //Navn på stævnet
                "12-05-2026"
        );

        swimmer.addResult(result1);

        System.out.println(swimmer);



//
//
//        Scanner scanner = new Scanner(System.in);
//
//        Membership active = new ActiveMembership();
//        Membership passive = new PassiveMembership();
//        SortMembersUseCaseImpl members = new SortMembersUseCaseImpl();
//
//
//        members.addMember("Jørgen", 1940, true, "konkurrencesvømmere", active);
//        members.addMember("Hans Hansen", 2022, true, "konkurrencesvømmer", active);
//        members.addMember("Gert Gertsen", 1920, true, "konkurrencesvømmer", passive);
//        members.addMember("Cay", 1975, true, "konkurrencesvømmer", active);
//
//
//        for (Member member : members.getMembers()) {
//            memberRepository.save(member);
//        }
//
//
//
//        System.out.println(
//                String.format(
//                        "the saved members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );
//        memberRepository.delete(members.getMembers().get(0).getId());
//
//        System.out.println(
//                String.format(
//                        "after deleting the saved members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );
//
//        Member newMember = new Member(999, "Ronald McDonald", 1940, true, "konkurrencesvømmere", active);
//
//        memberRepository.save(newMember);
//
//        System.out.println(
//                String.format(
//                        "after adding Ronald McDonald the members are \n-------------\n:%s",
//                        memberRepository.findAll()
//                )
//        );

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