package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterBestTrainingResultUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.model.TrainingResult;

public class RegisterBestTrainingResultUseCaseImpl implements RegisterBestTrainingResultUseCase {
    private MemberRepository memberRepository;

    public RegisterBestTrainingResultUseCaseImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;


    }

    public void execute(int id, double time, String discipline) {
        if (memberRepository.exists(id)) {
            Member member = memberRepository.find(id);
            TrainingResult result = new TrainingResult(time, discipline);
            member.addTrainingResult(result);
            memberRepository.delete(id);
            memberRepository.save(member);



        }


    }
}
