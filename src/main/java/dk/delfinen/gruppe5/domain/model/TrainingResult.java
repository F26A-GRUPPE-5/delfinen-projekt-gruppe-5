package dk.delfinen.gruppe5.domain.model;


public class TrainingResult {
    private double time;
    private String discipline;

    public TrainingResult(double time, String discipline) {
        this.time = time;
        this.discipline = discipline;
    }

    public double getTime() {
        return time;
    }

    public String getDiscipline() {
        return discipline;
    }
}
