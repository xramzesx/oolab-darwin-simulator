package oolab.darwin.elements;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.Genome;
import oolab.darwin.enums.MapDirection;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.stats.AnimalStats;

import java.util.ArrayList;

public class Animal extends AbstractMapElement  {

    //// MAIN PARAMS ////
    public MapDirection direction;
    public int energy;

    public int birthDate = 0;
    public int deathDate = -1;

    private int currentGenome = 0;
    private final ArrayList<Genome> genomes;

    private int children = 0;

    public int eatenPlants = 0;

    //// GLOBALS ////
    private final Config config;
    private final IWorldMap map;


    public Animal(
        Config config,
        IWorldMap map,
        ArrayList<Genome> genomes,
        Vector2d position
    ){
        this.map = map;
        this.config = config;
        this.position = position;
        this.direction = MapDirection.random();
        this.genomes = genomes;
        this.energy = config.initialAnimalEnergy;
    }

    public Animal(
        Config config,
        IWorldMap map,
        ArrayList<Genome> genomes,
        Vector2d position,
        int energy,
        int birthDate
    ) {
        this(config, map, genomes, position);
        this.energy = energy;
        this.birthDate = birthDate;
    }

    //// BEHAVIOR ///

    private void mutate() {

    }

    public void eat( Plant plant ){
        changeEnergy(plant.energy);
        eatenPlants++;
        System.out.println(toString() + " " + this.energy);
    }

    public void move() {
        if (
            config.animalBehaviorVariant == AnimalBehaviorVariant.DEVIATION
            && Utils.drawResult(20)
        ){
            currentGenome = Utils.getAnotherIndex(
                currentGenome,
                genomes.size()
            );
        } else {
            currentGenome = (currentGenome + 1) % genomes.size();
        }



        //// SETUP NEW POSITION ////

        Vector2d prevPosition = this.position;

        this.direction = this.direction.rotate(
            genomes.get(currentGenome)
        );

        this.position = this.position.add(
            this.direction.toUnitVector()
        );

        //// PLACE ////

        this.map.place(this, prevPosition);
    }

    public Animal multiply( Animal animal, int birthDate ) {

        int childrenEnergy = config.multiplicationEnergy * 2;

        Animal child = new Animal(
            config,
            map,
            Genome.evolve(
                config,
                this,
                animal
            ),
            this.position,
            childrenEnergy,
            birthDate
        );

        this.changeEnergy( -config.multiplicationEnergy );
        animal.changeEnergy( -config.multiplicationEnergy );

        this.children++;
        animal.children++;

        return child;
    }

    public void kill (int deathDate) {
        this.deathDate = deathDate;
    }

    //// HEALTH ////


    public boolean isDead() {
        return this.energy <= 0;
    }

    public boolean isStuffed() { return this.energy >= this.config.stuffedEnergy; };

    public boolean isFertile() { return this.isStuffed() && this.energy >= this.config.multiplicationEnergy; }

    public void changeEnergy( int difference ) {
        this.energy = Math.max( this.energy + difference, 0 );
    }


    //// GETTERS ////

    public int getAge( int currentDay) {
        return currentDay - birthDate;
    }

    public int getChildren() {
        return children;
    }

    public ArrayList<Genome> getGenomes() {
        return genomes;
    }

    public Genome getGenome() {
        return genomes.get(currentGenome);
    }

    public AnimalStats getStats( int currentDay ) {
        return isDead()
            ? new AnimalStats(this)
            : new AnimalStats(this, currentDay );
    }

    //// FOR DEBUG ////

    @Override
    public String toString() {
        return "(" + this.position.toString() + "," + this.direction.toString() + ")";
    }
}
