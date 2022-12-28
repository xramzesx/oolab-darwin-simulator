package oolab.darwin.stats;

import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;

import java.util.ArrayList;

public class AnimalStats {

    ArrayList<Genome> genomes;
    Genome currentGenome;

    Integer energy;
    Integer eatenPlants;
    Integer children;

    Integer birthdate;
    Integer deathdate;
    Integer age;

    public AnimalStats (Animal animal) {
        this.genomes = animal.getGenomes();
        this.currentGenome = animal.getGenome();
        this.energy = animal.energy;
        this.eatenPlants = animal.eatenPlants;
        this.children = animal.getChildren();
        this.birthdate = animal.birthdate;
        this.deathdate = animal.deathDate;
    }

    public AnimalStats( Animal animal, int currentDay ) {
        this(animal);
        this.age = currentDay - birthdate;
    }

    public Integer getAge() {
        return age == null ? deathdate - birthdate : age;
    }
}
