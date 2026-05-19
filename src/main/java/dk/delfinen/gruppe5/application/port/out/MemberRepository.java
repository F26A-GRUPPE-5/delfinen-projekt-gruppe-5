package dk.delfinen.gruppe5.application.port.out;

import dk.delfinen.gruppe5.domain.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: Dennes job er ved Dependency inversion at sørge for at application laget ikke ved noget om persistence laget. ports skal være meget simple. hvad skal gettes, hvad skal settes.
public interface MemberRepository {
    Optional<Member> find(int memberId);
    boolean exists(int id);
    void save(Member member);
    void delete(int id);
    List<Member> findAll();
    void clearAll();
    void saveAll(List<Member> members);
}