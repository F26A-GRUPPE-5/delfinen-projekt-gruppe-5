package dk.delfinen.gruppe5.application.dto;

public class MemberDTO {
    public int id;
    public String name;
    public int birthYear;
    public boolean isCompetitor;
    public String activity;
    public boolean hasPaid;

    public MemberDTO(int id, String name, int birthYear, boolean isCompetitor, String activity, boolean hasPaid) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.isCompetitor = isCompetitor;
        this.activity = activity;
        this.hasPaid = hasPaid;
    }
}
