package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.application.service.EditMemberCommand;
import dk.delfinen.gruppe5.domain.service.Membership;

public interface EditMemberUseCase {
    void execute (EditMemberCommand command);
}
