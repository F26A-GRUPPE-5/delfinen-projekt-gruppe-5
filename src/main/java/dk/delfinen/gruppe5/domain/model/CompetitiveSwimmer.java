package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;

public class CompetitiveSwimmer {

    private Member member;

    private Trainer trainer;

    private ArrayList<Result> results;

    //kontruktør
    public CompetitiveSwimmer(Member member, Trainer trainer) {
        this.member = member;
        this.trainer = trainer;
        results = new ArrayList<>();
    }

    // getter
    public Member getMember() {
        return member;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public ArrayList<Result> getResults() {
        return results;
    }


    // setter
    public void setMember(Member member) {
        this.member = member;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "CompetitiveSwimmer{" +
                "member=" + member +
                ", trainer=" + trainer +
                ", results=" + results +
                '}';
    }
}
