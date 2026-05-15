package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;

public class CompetitiveSwimmer {

    //Variabler:
    private Member member;

    private Trainer trainer;

    // Liste som gemmer svømmerens resultater
    // ArrayList betyder at vi kan gemme flere Result objekter.
    private ArrayList<Result> results;

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
        results = new ArrayList<>();
        // Opretter en tom liste til discipliner. Så vi senere kan tilføje discipliner med add()
        //Sagt med andre ord: Her bliver listen faktisk oprettet i hukommelsen. (oprettelse/initialisering)
        //Hvis denne ikke laves crasher programet med besked: NullPointerException. Andre ord: “Der findes ingen rigtig liste endnu”
        disciplines = new ArrayList<>();
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


    public ArrayList<Result> getResults() {
        return results;
    }
    public void addResult(Result result) {
        results.add(result);
    }


    // setter
    public void setMember(Member member) {
        this.member = member;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    // Metode som tildeler/ændrer træner
    public static void addCoachToCompSwimmer(
            CompetitiveSwimmer swimmer,
            Trainer trainer
    ) {

        // Validering (Kun hvis træneren og navnet findes og navnet ikke er tomt)
        if (trainer != null && //Findes der et trainer objekt
                trainer.getName() != null &&
                !trainer.getName().isEmpty()) {
            swimmer.setTrainer(trainer);

        } //ved denne undgår vi tomme navne, null errors og ugyldige træner
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






