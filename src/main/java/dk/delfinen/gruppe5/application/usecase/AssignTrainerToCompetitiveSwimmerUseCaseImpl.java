package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.AssignTrainerToCompetitiveSwimmerUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

public class AssignTrainerToCompetitiveSwimmerUseCaseImpl implements AssignTrainerToCompetitiveSwimmerUseCase {
    private MemberRepository memberRepository;


    public AssignTrainerToCompetitiveSwimmerUseCaseImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }
    public void execute(int id, String name) {
        if (memberRepository.exists(id)) {
            Member member = memberRepository.find(id);
            member.setTrainerName(name);
            memberRepository.delete(id);
            memberRepository.save(member);





        }


    }
}
