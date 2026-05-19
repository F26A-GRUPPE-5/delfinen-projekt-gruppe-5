package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.CalculateMembershipFeeUseCase;
import dk.delfinen.gruppe5.application.port.in.ClockObj;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

import java.time.ZoneId;

public class CalculateMembershipFeeUseCaseImpl implements CalculateMembershipFeeUseCase {

    private final ClockObj clock;
    private final MemberRepository repo;

    public CalculateMembershipFeeUseCaseImpl(ClockObj clock, MemberRepository repo) {
        this.clock = clock;
        this.repo = repo;
    }

    private int getAge(Member member) {
        int currentYear = clock.now().getYear();
        return currentYear - member.getBirthYear();
    }

    public double execute(int id) {
        Member member = repo.find(id).orElseThrow( () -> new RuntimeException("Member not found"));
        return member.getMembership().calculateFee(getAge(member));
    }
}
