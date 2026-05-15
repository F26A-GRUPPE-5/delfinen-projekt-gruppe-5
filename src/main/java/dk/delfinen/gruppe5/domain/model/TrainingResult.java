package dk.delfinen.gruppe5.domain.model;


public class TrainingResult {
    private int time;
    private String discipline;

    public TrainingResult(int time, String discipline) {
        this.time = time;
        this.discipline = discipline;
    }

    public int getTime() {
        return time;
    }

    public String getDiscipline() {
        return discipline;
    }
}
