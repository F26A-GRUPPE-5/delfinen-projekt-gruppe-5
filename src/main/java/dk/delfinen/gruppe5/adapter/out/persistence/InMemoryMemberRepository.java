package dk.delfinen.gruppe5.adapter.out.persistence;

import dk.delfinen.gruppe5.application.port.out.IdGenerator;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.application.service.RandomIdGenerator;
import dk.delfinen.gruppe5.domain.model.Member;
import java.util.*;

public class InMemoryMemberRepository implements MemberRepository{
    private IdGenerator idGenerator;
    private HashMap<Integer, Member> members;

    public InMemoryMemberRepository() {
        this.members = new HashMap<>();
        idGenerator = new RandomIdGenerator();
    }

    @Override
    public Member find(int id) {
        return members.get(id);
    }

    @Override
    public boolean exists(int id) {
        return members.containsKey(id);
    }

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public void delete(int id) {
        members.remove(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    @Override
    public void clearAll() {
        members = new HashMap<>();
    }

    @Override
    public void saveAll(List<Member> members) {
        for (Member member : members) {
            save(member);
        }
    }


    @Override
    public String toString() {
        return findAll().toString();
    }
}

