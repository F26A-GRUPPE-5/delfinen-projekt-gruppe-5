package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.CalculateMembershipFeeUseCase;
import dk.delfinen.gruppe5.application.port.in.ViewExpectedYearlyIncomeUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.ArrayList;
import java.util.List;

public class ViewExpectedYearlyIncomeUseCaseImpl implements ViewExpectedYearlyIncomeUseCase {

    MemberRepository repo;
    CalculateMembershipFeeUseCase calculateFee;

    public ViewExpectedYearlyIncomeUseCaseImpl(MemberRepository repo, CalculateMembershipFeeUseCase calculateFee) {
        this.repo = repo;
        this.calculateFee = calculateFee;
    }

    public double execute() {
        double sum = 0;
        List<Member> members = repo.findAll();
        for (Member member : members) {
            sum += calculateFee.execute(member.getId());
        }
        return sum ;
    }
}
