package dk.delfinen.gruppe5;

import java.time.Year;

public class Member {
    private String name;
    private int bithYear;
    private String activity;
    private boolean isActive;
    private int birthYear;
    private boolean isCompetitor;

    public Member(String name, int birthYear, boolean isActive, boolean isCompeditor, String activity) {
        this.name = name;
        this.birthYear = birthYear;
        this.isActive = isActive;
        this.isCompetitor = isCompeditor;
        this.activity = activity;
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

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return String.format("""
    navn: %s
    alder: %s
    aktiv: %s
    aktivitet: %s
    
    """,
                getName(),
                getAge(),
                isActive(),
                getActivity()
                );
    }
}
