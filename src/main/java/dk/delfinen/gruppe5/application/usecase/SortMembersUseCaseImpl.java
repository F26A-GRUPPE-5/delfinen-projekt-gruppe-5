package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.SortMembersUseCase;
import dk.delfinen.gruppe5.domain.model.Member;

import java.text.Collator;
import java.util.*;

//TODO:
// arbejdsgange og orkestreringen som skal leve her.
// Bør ikke forvæksles med domain services, hvor business rules lever.
public class SortMembersUseCaseImpl implements SortMembersUseCase {

    public ArrayList<Member> sortByActivity(List<Member> members) {
        Comparator<Member> byActivity = new Comparator<Member>() {
            Collator danishCollator = Collator.getInstance(Locale.forLanguageTag("da-DK"));
            @Override
            public int compare(Member member1, Member member2) {
                return danishCollator.compare(member1.getActivity(), member2.getActivity());
            }
        };
        List<Member> sorted = new ArrayList<>(members);
        sorted.sort(byActivity);

        return new ArrayList<>(sorted);
    }
}
