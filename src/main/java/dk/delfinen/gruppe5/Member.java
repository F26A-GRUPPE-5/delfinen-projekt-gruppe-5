package dk.delfinen.gruppe5;

import java.time.Year;

public class Member {
    private String name;
    private String activity;
    private int birthYear;
    private Membership membership;
    private boolean isCompetitor;

    public Member(String name, int birthYear, boolean isCompeditor, String activity, Membership membership) {
        this.name = name;
        this.birthYear = birthYear;
        this.isCompetitor = isCompeditor;
        this.activity = activity;
        this.membership = membership;
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

    @Override
    public String toString() {
        return String.format("""
    navn: %s
    alder: %s
    membership: %s
    aktivitet: %s
    
    """,
                getName(),
                getAge(),
                membership.getType(),
                getActivity()
                );
    }
}
