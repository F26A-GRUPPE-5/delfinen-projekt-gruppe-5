package dk.delfinen.gruppe5;

import java.time.Year;

public class Member {
    private String name;
    private String activity;
    private int birthYear;
    private Membership membership;
    private boolean isCompetitor;
    private int memberId;

    public Member(String name, int birthYear, boolean isCompeditor, String activity, Membership membership, int memberId) {
        this.name = name;
        this.birthYear = birthYear;
        this.isCompetitor = isCompeditor;
        this.activity = activity;
        this.membership = membership;
        this.memberId = memberId;

    }

    public String getActivity() {
        return activity;
    }
    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getMemberId() {
        return memberId;
    }

    public double getFee() {
        return membership.calculateFee(getAge());
    }

    @Override
    public String toString() {
        return String.format("""
    meldemId: %s
    navn: %s
    alder: %s
    membership: %s
    aktivitet: %s
    
    """,
                getMemberId(),
                getName(),
                getAge(),
                membership.getType(),
                getActivity()
                );
    }
}
