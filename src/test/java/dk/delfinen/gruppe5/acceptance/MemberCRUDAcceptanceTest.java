package dk.delfinen.gruppe5.acceptance;

import dk.delfinen.gruppe5.adapter.out.persistence.FakeMemberRepository;
import dk.delfinen.gruppe5.application.port.in.DeleteMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.usecase.DeleteMemberUseCaseImpl;
import dk.delfinen.gruppe5.domain.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberCRUDAcceptanceTest {


        private MemberRepository repo;
        private DeleteMemberUseCase useCase;

        @BeforeEach
        void setUp() {
            repo = new FakeMemberRepository();
            useCase = new DeleteMemberUseCaseImpl(repo);
        }

//        @Test
//        void user_can_delete_existing_member() {
//            repo.save(new Member(1, "John"));
//
//            var result = useCase.execute(1);  // we should use explicit error values instead of exceptions in inner layers because it is less messy when they propagate through other layers. also if there are multiple errors we will only see one.
//            assertTrue(result.isSuccess());
//            assertFalse(repo.exists(1));
//        }
//    }
}
