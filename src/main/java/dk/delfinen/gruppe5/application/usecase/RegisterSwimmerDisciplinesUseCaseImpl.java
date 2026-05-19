package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterSwimmerDisciplinesUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

public class RegisterSwimmerDisciplinesUseCaseImpl implements RegisterSwimmerDisciplinesUseCase {

    private MemberRepository memberRepository;

    public RegisterSwimmerDisciplinesUseCaseImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void execute(int id, String discipline) {
        if (memberRepository.exists(id)) {
            Member member = memberRepository.find(id);
            member.addDiscipline(discipline);
            memberRepository.delete(id);
            memberRepository.save(member);
        }
    }
}

