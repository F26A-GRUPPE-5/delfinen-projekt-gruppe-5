package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.domain.model.Member;

import java.util.ArrayList;
import java.util.List;

public interface SortMembersUseCase {
    ArrayList<Member> sortByActivity(List<Member> members);
}
