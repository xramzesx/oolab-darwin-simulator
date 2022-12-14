package oolab.darwin.engines;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IWorldMap;

public class SimulationEngine implements IEngine {

    IWorldMap map;
    Config config;

    //// INIT ////

    public SimulationEngine( IWorldMap map, Config config ) {
        this.map = map;
        this.config = config;
    }

    //// STEPS ////

    private void clearCorpse() {

    }

    private void moveAnimals() {

    }

    private void resolveConflicts() {

    }

    private void resolveConflict( Vector2d position) {

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
