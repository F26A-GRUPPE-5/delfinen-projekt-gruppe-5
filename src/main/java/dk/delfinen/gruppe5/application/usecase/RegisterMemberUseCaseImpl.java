package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.RegisterMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

public class RegisterMemberUseCaseImpl implements RegisterMemberUseCase {
    private IdGenerator idGenerator;
    private MemberRepository memberRepository;

    public RegisterMemberUseCaseImpl(IdGenerator idGenerator, MemberRepository memberRepository) {
        this.idGenerator = idGenerator;
        this.memberRepository = memberRepository;
    }

    @Override
    public void execute (String name, int birthYear, boolean isCompetitor, String activity, boolean isActive) {
        int id = idGenerator.nextId();
        Member member = new Member(id, name, birthYear, isCompetitor, activity,
                isActive?
                new ActiveMembership()
                : new PassiveMembership()
        );
        memberRepository.save(member);
    }
}
