package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.EditMemberCommand;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Optional;

public interface FindMemberUseCase {
   Optional<Member> execute (int id);
}
