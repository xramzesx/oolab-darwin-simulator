package oolab.darwin.stats;

import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;

import java.util.ArrayList;

public class AnimalStats {

    public ArrayList<Genome> genomes;
    public Genome currentGenome;

    public Integer energy;
    public Integer eatenPlants;
    public Integer children;

    public Integer birthDate;
    public Integer deathDate;
    public Integer age;

    public AnimalStats (Animal animal) {
        this.genomes = animal.getGenomes();
        this.currentGenome = animal.getGenome();
        this.energy = animal.energy;
        this.eatenPlants = animal.eatenPlants;
        this.children = animal.getChildren();
        this.birthDate = animal.birthDate;
        this.deathDate = animal.deathDate;
    }

    public AnimalStats( Animal animal, int currentDay ) {
        this(animal);
        this.age = currentDay - birthDate;
    }

    public Integer getAge() {
        return age == null ? deathDate - birthDate : age;
    }
}
