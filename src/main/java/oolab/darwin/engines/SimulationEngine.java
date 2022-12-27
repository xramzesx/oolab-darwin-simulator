package oolab.darwin.engines;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IObserver;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    public int day = 0;

    IWorldMap map;
    Config config;

    IObserver observer;

    //// INIT ////

    public SimulationEngine(
        Config config,
        IWorldMap map,
        ArrayList<Vector2d> animalPositions,
        IObserver observer
    ) {
        this.map = map;
        this.config = config;
        this.observer = observer;
        for ( Vector2d position : animalPositions ) {
            Animal animal = new Animal(
                this.config,
                this.map,
                Genome.generate(config),
                position
            );

            map.place(animal, null);
        }

    }

    //// STEPS ////

    private void clearCorpse() {

    }

    private void moveAnimals() {
        this.map.move();
    }

    private void resolveConflicts() {

    }

    private void resolveConflict( Vector2d position ) {

    }

    private void multiplyAnimals() {

    }

    private void renewPlants() {

    }

    private void simulateDay() {
        clearCorpse();
        moveAnimals();
        resolveConflicts();
        multiplyAnimals();
        renewPlants();
    }

    //// INTERFACE ////

    @Override
    public void run() {
        for ( int i = 0; i < config.genomeLength * 5; i++ ) {
            simulateDay();
            this.observer.positionChanged(null, null);
        }
    }
}
