package dk.delfinen.gruppe5.adapter.in;


import dk.delfinen.gruppe5.application.dto.MemberDTO;
import dk.delfinen.gruppe5.application.port.in.FindMemberUseCase;
import dk.delfinen.gruppe5.application.port.in.ListMembersUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.CompetitiveSwimmer;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.model.MeetResult;
import dk.delfinen.gruppe5.domain.model.TrainingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;


// TODO: her skal blandt andet formatering være, for eksempel hvis vi vil have farver eller skal vise noget i et table
//  det kan også handle om hvad der skal vises og skjules, eller catching errors og printe dem til brugeren på en pæn måde
public class Presenter {
    MemberRepository members;

    ListMembersUseCase listMembers;
    
    public Presenter(MemberRepository members, ListMembersUseCase listMembers) {
        this.members = members;
        this.listMembers = listMembers;
    }

    public String MemberList() {
        String string = "";
        List<MemberDTO> list = listMembers.execute();
        for (MemberDTO memberDTO : list) {
            string +=
                    String.format("""
                            id: %s
                            navn: %s
                            fødselsår: %s
                            konkurrerende:
                            aktivitet:
                            
                            """,
                            memberDTO.id,
                            memberDTO.name,
                            memberDTO.birthYear,
                            memberDTO.isCompetitor,
                            memberDTO.activity);
        }
        return string;
    }

    


    public void printTopFiveTrainingResults(CompetitiveSwimmer swimmer) {
        for (TrainingResult trainingResult : swimmer.getTopFiveTrainingResults()) {
            System.out.println(trainingResult);

        }
    }

    public String SubscriptionPaidList() {
        String string = "";
        List<MemberDTO> list = listMembers.execute();
        for (MemberDTO memberDTO : list) {
            string +=
                    String.format("""
                            id: %s
                            navn: %s
                            betalt? : %s
                            """,
                            memberDTO.id,
                            memberDTO.name,
                            memberDTO.hasPaid);
        }
        return string;
    }

    public String SubscriptionUnpaidList() {
        String string = "";
        for (Member m : members.findAll()) {
            string += m;
        }
        return string;
    }
}


