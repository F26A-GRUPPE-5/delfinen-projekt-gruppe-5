package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.application.port.out.MemberRepository;

public interface CalculateMembershipFeeUseCase {
    double execute (int id);
}
