package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;

public class Result {
    private int time;
    private String discipline;

    public Result(int time, String discipline) {
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
