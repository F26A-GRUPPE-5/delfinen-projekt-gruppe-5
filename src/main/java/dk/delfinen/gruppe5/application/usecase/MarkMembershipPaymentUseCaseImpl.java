package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.MarkMembershipPaymentUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Optional;

public class MarkMembershipPaymentUseCaseImpl implements MarkMembershipPaymentUseCase {

    private final MemberRepository repo;

    public MarkMembershipPaymentUseCaseImpl(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean execute(int memberId) {
        Optional<Member> memberOption = repo.find(memberId);

        if (memberOption.isEmpty()) {
            return false;
        }
        Member member = memberOption.get();
        member.setHasPaid(true);
        repo.save(member);
        return true;
    }
}
