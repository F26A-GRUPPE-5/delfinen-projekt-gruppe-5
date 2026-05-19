package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.application.dto.MemberDTO;

import java.util.List;

public interface ListMembersUseCase {
    List<MemberDTO> execute();
}
