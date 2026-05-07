import java.time.Year;

public class Member {
    private String name;
    private int birthYear;
    private boolean isActive;
    private boolean isCompeditor;

    public Member(String name, int birthYear, boolean isActive, boolean isCompeditor) {
        this.name = name;
        this.birthYear = birthYear;
        this.isActive = isActive;
        this.isCompeditor = isCompeditor;
    }
    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - birthYear;
    }
    public boolean isJunior() {
        return getAge() <18;

    }

    public boolean isSenior() {
        return getAge() >= 18;
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

    public boolean isCompeditor() {
        return isCompeditor;
    }
}
