package dk.delfinen.gruppe5.acceptance;

import dk.delfinen.gruppe5.adapter.out.persistence.FakeMemberRepository;
import dk.delfinen.gruppe5.application.port.in.DeleteMemberUseCase;
import dk.delfinen.gruppe5.application.port.in.RegisterMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.FakeIdGenerator;
import dk.delfinen.gruppe5.application.service.RandomIdGenerator;
import dk.delfinen.gruppe5.application.usecase.DeleteMemberUseCaseImpl;
import dk.delfinen.gruppe5.application.usecase.RegisterMemberUseCaseImpl;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberCRUDAcceptanceTest {


    private MemberRepository repo;
    private DeleteMemberUseCase deleteMember;
    private RegisterMemberUseCase registerMember;

    @BeforeEach
    void setUp() {
        repo = new FakeMemberRepository();

    }


    @Test
    void user_can_delete_existing_member() {
        deleteMember = new DeleteMemberUseCaseImpl(repo);
        repo.save(new Member(1,"jens", 1992, true, "sleepwalker", new ActiveMembership()));
        deleteMember.execute(1);
    //    assertTrue(result.isSuccess());  //we should probably do this to get error messaging to ui
        assertFalse(repo.exists(1));
    }

    @Test
    void user_can_register_member() {
        int id = 1;

        registerMember = new RegisterMemberUseCaseImpl(new FakeIdGenerator(1), repo);
        registerMember.execute("Jens", 1992, true, "sleepwalker", new ActiveMembership());

        assertTrue(repo.exists(id));
    }

    @Test
    void user_can_edit_member() {
    }

    @Test
    void user_can_view_member() {
    }
}

