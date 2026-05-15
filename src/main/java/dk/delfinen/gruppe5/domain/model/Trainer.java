package dk.delfinen.gruppe5.domain.model;

public class Trainer {

    // Instansvariabel som gemmer trænerens navn
    private String name;

    // Constructor som bruges når man opretter en Trainer
    public Trainer(String name) {
        this.name = name;  //Gemmer parameterens værdi i instansvariablen

    }


    //getter som returnerer trænerens navn
    public String getName() {
        return name;
    }

    //setter som kan ændre trænerens navn
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                '}';
    }
}
