package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterCompetitionResultCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Discipline;
import dk.delfinen.gruppe5.domain.model.Member;
import  dk.delfinen.gruppe5.domain.model.MeetResult;

import java.util.Optional;

public class RegisterCompetitionResultUseCaseImpl implements RegisterCompetitionResultCase {
    private MemberRepository memberRepository;

    public RegisterCompetitionResultUseCaseImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public boolean execute(int id,
                           Discipline discipline,
                           int placement,
                           String competition,
                           String date) {

        Optional<Member> memberOpt = memberRepository.find(id);

        if (memberOpt.isEmpty()) {
            return false;
        }

        Member member = memberOpt.get();
        MeetResult meetResult = new MeetResult(discipline, placement, competition, date);
        member.addMeetResult(meetResult);
        memberRepository.delete(id);
        memberRepository.save(member);
        return true;
    }
}
