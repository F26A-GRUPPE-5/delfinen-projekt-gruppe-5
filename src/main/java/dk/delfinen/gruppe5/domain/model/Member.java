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
    private boolean hasPaid;
    private String trainerName;


    public Member(int id, String name, int birthYear, boolean isCompetitor, String activity, Membership membership) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.isCompetitor = isCompetitor;
        this.activity = activity;
        this.membership = membership;
        this.hasPaid = false;
    }

    public Membership getMembership() {
        return membership;
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

    public boolean getHasPaid() {
        return hasPaid;
    }

    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - birthYear;
    }

    public double getFee() {
        return membership.calculateFee(getAge());
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setIsCompetitor(Boolean isCompetitor) {
        this.isCompetitor = isCompetitor;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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

