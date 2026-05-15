package dk.delfinen.gruppe5.domain.model;

public class MeetResult {

    // Hvilken disciplin resultatet tilhører
    private Discipline discipline;

    // Svømmetid
    private double time;

    // Placering i konkurrencen
    private int placement;

    // Navn på stævnet
    private String competition;

    // Dato for resultatet
    private String date;


    // Constructor
    public MeetResult(Discipline discipline,
                      double time,
                      int placement,
                      String competition,
                      String date) {

        this.discipline = discipline;
        this.time = time;
        this.placement = placement;
        this.competition = competition;
        this.date = date;
    }


    // Getters
    public Discipline getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public int getPlacement() {
        return placement;
    }

    public String getCompetition() {
        return competition;
    }

    public String getDate() {
        return date;
    }


    // Setters
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Result{" +
                "discipline=" + discipline +
                ", time=" + time +
                ", placement=" + placement +
                ", competition='" + competition + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}