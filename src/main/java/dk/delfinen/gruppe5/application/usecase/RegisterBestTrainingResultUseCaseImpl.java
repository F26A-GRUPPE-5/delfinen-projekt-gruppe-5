package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.adapter.in.InvalidInputException;
import dk.delfinen.gruppe5.application.port.in.RegisterBestTrainingResultUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.model.TrainingResult;

import java.util.Optional;

public class RegisterBestTrainingResultUseCaseImpl implements RegisterBestTrainingResultUseCase {
    private MemberRepository memberRepository;

    public RegisterBestTrainingResultUseCaseImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;


    }

    public boolean execute(int id, double time, String discipline) {
        Optional<Member> memberOpt = memberRepository.find(id);

        if (memberOpt.isEmpty()) {
            return false;
        }

        Member member = memberOpt.get();
        TrainingResult result = new TrainingResult(time, discipline);
        member.addTrainingResult(result);

        memberRepository.delete(id);
        memberRepository.save(member);

        return true;
    }
}
