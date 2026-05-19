package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.ListMembersUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.dto.MemberDTO;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.ArrayList;
import java.util.List;

public class ListMembersUseCaseImpl implements ListMembersUseCase {
    MemberRepository repo;

    public ListMembersUseCaseImpl(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<MemberDTO> execute() {
        List<MemberDTO> lines = new ArrayList<>();
        List<Member> members = repo.findAll();
        for (Member member : members) {
            lines.add(new MemberDTO(
                    member.getId(),
                    member.getName(),
                    member.getBirthYear(),
                    member.getIsCompetitor(),
                    member.getActivity(),
                    member.getHasPaid()));
        }
        return lines;
    }
}
