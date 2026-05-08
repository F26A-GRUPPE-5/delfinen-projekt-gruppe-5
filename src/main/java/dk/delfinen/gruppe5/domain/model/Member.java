package dk.delfinen.gruppe5.domain.model;

import dk.delfinen.gruppe5.domain.service.Membership;

import java.time.Year;

// Her i model lever Entities, inklusiv Business Rules, altså regler for hvad man må og ikke må.

public class Member {
    private final int id;
    private String name;
    private int birthYear;
    private boolean isCompetitor;
    private String activity;
    private Membership membership;


    public Member(int id, String name, int birthYear, boolean isCompeditor, String activity, Membership membership) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.isCompetitor = isCompeditor;
        this.activity = activity;
        this.membership = membership;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public boolean getIsCompetitor() {
        return isCompetitor;
    }

    public String getActivity() {
        return activity;
    }


    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - birthYear;
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
                getId(),
                getName(),
                getAge(),
                membership.getType(),
                getActivity()
        );
    }
}

