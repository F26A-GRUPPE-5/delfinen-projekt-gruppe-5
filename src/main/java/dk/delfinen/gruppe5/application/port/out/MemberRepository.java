package dk.delfinen.gruppe5.application.port.out;

import dk.delfinen.gruppe5.domain.model.Member;

import java.util.List;

//TODO: Dennes job er ved Dependency inversion at sørge for at application laget ikke ved noget om persistence laget. ports skal være meget simple. hvad skal gettes, hvad skal settes.
public interface MemberRepository {
    Member find(int id); // her bør der være en runtime exception hvis den nu ikke findes
    boolean exists(int id);
    void save(Member member);
    void delete(int id);
    List<Member> findAll();
    void clearAll();
    void saveAll(List<Member> members);
}
