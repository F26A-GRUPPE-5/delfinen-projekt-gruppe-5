package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterCompetitionResultCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Discipline;
import dk.delfinen.gruppe5.domain.model.Member;
import  dk.delfinen.gruppe5.domain.model.MeetResult;

public class RegisterCompetitionResultUseCaseImpl implements RegisterCompetitionResultCase {
    private MemberRepository memberRepository;

    public RegisterCompetitionResultUseCaseImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void execute(int id, Discipline discipline, int placement, String competition, String date) {
        if (memberRepository.exists(id)) {
            Member member = memberRepository.find(id);
            MeetResult meetResult = new MeetResult(discipline, placement, competition, date);
            member.addMeetResult(meetResult);
            memberRepository.delete(id);
            memberRepository.save(member);

        }


    }
}
