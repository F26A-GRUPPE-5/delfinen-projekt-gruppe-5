package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.Membership;

import java.text.Collator;
import java.util.*;

//TODO: listen af members bør ikke leve her men i filen, så vi har Single Source of truth.
// metoderne (use cases) til Members i flertal skal leve her og kan kaldes Services.
// Det er altså her arbejdsgange og orkestreringen som skal leve her.
// Bør ikke forvæksles med domain services, hvor business rules lever.
public class MemberList {
    private ArrayList<Member> members;
    private HashSet<Integer> usedIds;
    private Random random;

    public MemberList() {
        members = new ArrayList<>();
        usedIds = new HashSet<>();
        random = new Random();
    }

    public void setMembers(List<Member> membersFromFile) {
        this.members.addAll(membersFromFile);

        // keep usedIds in sync
        for (Member m : membersFromFile) {
            usedIds.add(m.getId());
        }
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member getMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void addMember(String name, int birthYear, boolean isCompetitor, String activity, Membership membership) {

        int id = generateMemberId();
        members.add(new Member(id, name, birthYear, isCompetitor, activity, membership));
    }

    public int generateMemberId() {
        int id;

        do {
            id = random.nextInt(900) + 100;
        } while(usedIds.contains(id));

        usedIds.add(id);
        return id;
    }


    public void sortByActivity() {
        Comparator<Member> byActivity = new Comparator<Member>() {
            Collator danishCollator = Collator.getInstance(Locale.forLanguageTag("da-DK"));
            @Override
            public int compare(Member member1, Member member2) {
                return danishCollator.compare(member1.getActivity(), member2.getActivity());
            }
        };
        members.sort(byActivity);
    }

    @Override
    public String toString() {
        return members.toString();
    }
}
