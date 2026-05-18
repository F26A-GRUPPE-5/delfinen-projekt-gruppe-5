package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.EditMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.EditMemberCommand;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Scanner;

public class EditMemberUseCaseImpl implements EditMemberUseCase {

    private final MemberRepository memberRepository;

    public EditMemberUseCaseImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void execute(EditMemberCommand command) {
        Member member = memberRepository.find(command.memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
