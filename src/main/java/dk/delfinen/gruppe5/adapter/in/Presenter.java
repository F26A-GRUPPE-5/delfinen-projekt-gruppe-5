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


public class Presenter {

    ListMembersUseCase listMembers;
    
    public Presenter(MemberRepository members, ListMembersUseCase listMembers) {
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
                            konkurrerende: %s
                            aktivitet: %s
                            
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

}


