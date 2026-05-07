package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.domain.model.Member;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

//TODO: listen af members bør ikke leve her men i filen, så vi har Single Source of truth.
// metoderne (use cases) til Members i flertal skal leve her og kan kaldes Services.
// Det er altså her arbejdsgange og orkestreringen som skal leve her.
// Bør ikke forvæksles med domain services, hvor business rules lever.
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
