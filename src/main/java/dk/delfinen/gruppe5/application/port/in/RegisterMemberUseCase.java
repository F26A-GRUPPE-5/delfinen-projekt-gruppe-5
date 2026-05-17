package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.domain.service.Membership;

public interface RegisterMemberUseCase {
    void execute (String name, int birthYear, boolean isCompetitor, String activity, boolean isActive);
}
