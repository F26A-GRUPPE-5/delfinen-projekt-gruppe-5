import java.time.Year;
import java.util.ArrayList;

public class Member {
    private String name;
    private int birthYear;
    private boolean isActive;
    private boolean isCompetitor;
    private static ArrayList<Member> members = new ArrayList<>();

    public Member(String name, int birthYear, boolean isActive, boolean isCompeditor) {
        this.name = name;
        this.birthYear = birthYear;
        this.isActive = isActive;
        this.isCompetitor = isCompeditor;
        members.add(this);
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

    // Hej test 5
}
