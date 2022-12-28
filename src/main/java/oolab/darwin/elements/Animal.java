package oolab.darwin.elements;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.Genome;
import oolab.darwin.enums.MapDirection;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class Animal extends AbstractMapElement  {

    //// MAIN PARAMS ////
    public MapDirection direction;
    public int energy;

    public int birthdate = 0;

    private int currentGenome = 0;
    private final ArrayList<Genome> genomes;

    private int children = 0;

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
    }

    public Animal(
        Config config,
        IWorldMap map,
        ArrayList<Genome> genomes,
        Vector2d position,
        int energy,
        int birthdate
    ) {
        this(config, map, genomes, position);
        this.energy = energy;
        this.birthdate = birthdate;
    }

    //// BEHAVIOR ///

    private void mutate() {

    }

    public void eat( Plant plant ){
        changeEnergy(plant.energy);
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

    public Animal multiply( Animal animal ) {
        //// TODO: add birth year
//        Animal child = new Animal(  )

        return null;
    }

    //// HEALTH ////


    public boolean isDead() {
        return this.energy <= 0;
    }

    public void changeEnergy( int difference ) {
        this.energy = Math.max( this.energy + difference, 0 );
    }


    //// GETTERS ////

    public int getChildren() {
        return children;
    }


    //// FOR DEBUG ////

    @Override
    public String toString() {
        return "(" + this.position.toString() + "," + this.direction.toString() + ")";
    }
}
