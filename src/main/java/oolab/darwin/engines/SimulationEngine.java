package oolab.darwin.engines;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    public int day = 0;

    IWorldMap map;
    Config config;

    //// INIT ////

    public SimulationEngine(
        Config config,
        IWorldMap map,
        ArrayList<Vector2d> animalPositions
    ) {
        this.map = map;
        this.config = config;

        for ( Vector2d position : animalPositions ) {
            Animal animal = new Animal( this.config, this.map, Genome.generate(config), position );
        }

    }

    //// STEPS ////

    private void clearCorpse() {

    }

    private void moveAnimals() {

    }

    private void resolveConflicts() {

    }

    private void resolveConflict( Vector2d position ) {

    }

    private void multiplyAnimals() {

    }

    private void renewPlants() {

    }

    //// INTERFACE ////

    @Override
    public void run() {

    }
}
