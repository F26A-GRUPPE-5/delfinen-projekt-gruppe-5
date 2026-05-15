package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompetitiveSwimmer {

    //Variabler:
    private Member member;

    private Trainer trainer;

    // Liste som gemmer svømmerens resultater
    // ArrayList betyder at vi kan gemme flere Result objekter.
    private ArrayList<MeetResult> meetResults;
    private ArrayList<TrainingResult> trainingResults;

    // ArrayList betyder at vi kan gemme flere Result objekter. foreksempel CRAWL, BUTTERFLY osv.
    // med andre ord “Der skal findes en variabel som hedder disciplines”. Men listen er endnu IKKE oprettet. Det sker nedenunder
    private ArrayList<Discipline> disciplines; //“Lav en privat variabel der er en liste af Discipline-objekter.”

    //kontruktør. Bruges når vi opretter en ny CompetitiveSwimmer
    public CompetitiveSwimmer(Member member, Trainer trainer) {
        // Gemmer member objektet i instansvariablen member
        this.member = member;
        // Gemmer trainer objektet i instansvariablen trainer
        this.trainer = trainer;
        // Opretter en tom liste til resultater. Så vi senere kan tilføje resultater med add()
        meetResults = new ArrayList<>();
        trainingResults = new ArrayList<>();
        // Opretter en tom liste til discipliner. Så vi senere kan tilføje discipliner med add()
        //Sagt med andre ord: Her bliver listen faktisk oprettet i hukommelsen. (oprettelse/initialisering)
        //Hvis denne ikke laves crasher programet med besked: NullPointerException. Andre ord: “Der findes ingen rigtig liste endnu”
        disciplines = new ArrayList<>();
    }
    public void addMeetResult(MeetResult meetResult) {
        meetResults.add(meetResult);
    }

    public void addTrainingResult(TrainingResult trainingResult) {
        trainingResults.add(trainingResult);
    }

    //Metoder
    // getter
    public Member getMember() {
        return member;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }
    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }


    public Trainer getTrainer() {
        return trainer;
    }


    public ArrayList<MeetResult> getResults() {
        return meetResults;
    }

    public ArrayList<TrainingResult> getTopFiveTrainingResults(){
        Collections.sort(trainingResults, Comparator.comparingDouble(TrainingResult::getTime));
        return new ArrayList<>(trainingResults.subList(0, Math.min(5, trainingResults.size())));
    }

    // setter
    public void setMember(Member member) {
        this.member = member;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setResults(ArrayList<MeetResult> meetResults) {
        this.meetResults = meetResults;
    }


    @Override
    public String toString() {
        return "CompetitiveSwimmer{" +
                "member=" + member +
                ", trainer=" + trainer +
                ", results=" + meetResults +
                ", disciplines=" + disciplines +
                '}';
    }

    public String getTeam() {
        if (member.getAge() <18) {
            return "Du tilhøre Ungdomshold";
        }else {
            return "Du tilhøre Seniorhold";
        }
    }
}






