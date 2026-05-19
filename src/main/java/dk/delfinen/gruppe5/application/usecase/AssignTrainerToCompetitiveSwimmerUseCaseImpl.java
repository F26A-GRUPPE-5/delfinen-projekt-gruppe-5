package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.AssignTrainerToCompetitiveSwimmerUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Optional;

public class AssignTrainerToCompetitiveSwimmerUseCaseImpl implements AssignTrainerToCompetitiveSwimmerUseCase {
    private MemberRepository memberRepository;


    public AssignTrainerToCompetitiveSwimmerUseCaseImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }
    public boolean execute(int id, String name) {
        Optional<Member> memberOpt = memberRepository.find(id);

        if (memberOpt.isEmpty()) {
            return false;
        }
        Member member = memberOpt.get();
        member.setTrainerName(name);
        memberRepository.delete(id);
        memberRepository.save(member);
        return true;
    }
}
