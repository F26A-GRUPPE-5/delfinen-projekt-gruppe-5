package dk.delfinen.gruppe5;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;

public class MemberList {
    private ArrayList<Member> members;

    public MemberList() {
        members = new ArrayList<>();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
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
