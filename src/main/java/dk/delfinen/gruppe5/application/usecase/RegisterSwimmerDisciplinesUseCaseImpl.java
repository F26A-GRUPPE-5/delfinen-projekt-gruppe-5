package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterSwimmerDisciplinesUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Optional;

public class RegisterSwimmerDisciplinesUseCaseImpl implements RegisterSwimmerDisciplinesUseCase {

    private MemberRepository memberRepository;

    public RegisterSwimmerDisciplinesUseCaseImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public boolean execute(int id, String discipline) {
        Optional<Member> memberOpt = memberRepository.find(id);
        if (memberOpt.isEmpty()) {
            return false;
        }
        Member member = memberOpt.get();
        member.addDiscipline(discipline);
        memberRepository.delete(id);
        memberRepository.save(member);
        return true;
    }
}

