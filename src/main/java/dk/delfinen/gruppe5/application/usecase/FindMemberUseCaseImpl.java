package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.FindMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Optional;

public class FindMemberUseCaseImpl implements FindMemberUseCase {
    MemberRepository repo;

    public FindMemberUseCaseImpl(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Member> execute(int id) {
        return repo.find(id);
    }
}
