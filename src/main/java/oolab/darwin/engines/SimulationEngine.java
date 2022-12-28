package oolab.darwin.engines;

import javafx.application.Platform;
import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;
import oolab.darwin.gui.SimulationView;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.interfaces.*;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    public int day = 0;

    private final IWorldMap map;
    private final Config config;

    private final ArrayList<IObserver> observers = new ArrayList<>();
    private SimulationView app;


    //// INIT ////

    public SimulationEngine(
        Config config,
        IWorldMap map,
        ArrayList<Vector2d> animalPositions,
        ArrayList<IObserver> observers,
        SimulationView app
    ) {
        this.map = map;
        this.config = config;
        this.app = app;

        for (IObserver observer: observers)
            subscribe(observer);

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

    /// ENGINE ///

    @Override
    public void run() {
        for ( int i = 0; i < config.genomeLength * 5; i++ ) {
            simulateDay();
            if(this.app != null) {
                 try {
                     Platform.runLater(app::renderGridPane);
                     Thread.sleep(this.config.refreshTime);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
            }
            this.signal();
        }
    }

    @Override
    public IWorldMap getWorldMap() {
        return map;
    }

    @Override
    public IMapBoundary getMapBoundary() {
        return map.getMapBoundary();
    }

    /// OBSERVABLE ///

    @Override
    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void signal() {
        for (IObserver observer : observers )
            observer.signal(this);
    }
}
