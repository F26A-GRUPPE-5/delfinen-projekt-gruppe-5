package dk.delfinen.gruppe5;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;

public class MemberList {
    private ArrayList<Member> members;
    private HashSet<Integer> usedIds;
    private Random random;

    public MemberList() {
        members = new ArrayList<>();
        usedIds = new HashSet<>();
        random = new Random();
    }


    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member getMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void addMember(String name, int birthYear, boolean isCompetitor, String activity, Membership membership) {

        int id = generateMemberId();
        members.add(new Member(name, birthYear, isCompetitor, activity, membership, id));
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
