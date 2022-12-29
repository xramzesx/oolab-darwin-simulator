package oolab.darwin.engines;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.enums.Genome;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.interfaces.*;
import oolab.darwin.stats.AnimalStats;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

public class SimulationEngine implements IEngine {

    public int day = 0;

    private final IWorldMap map;
    private final Config config;

    private final ArrayList<IObserver<IEngine>> observers = new ArrayList<>();


    //// INIT ////

    public SimulationEngine(
        Config config,
        IWorldMap map,
        ArrayList<Vector2d> animalPositions,
        ArrayList<IObserver<IEngine>> observers
    ) {
        this.map = map;
        this.config = config;

        for (IObserver<IEngine> observer: observers)
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

        this.map.spawnPlants(true);
    }

    //// STEPS ////

    private void sunRise() {
        for (Animal animal : map.getAnimals()) {
            animal.changeEnergy( -1 );
        }
    }


    private void clearCorpse() {
        for ( Animal animal : map.getAnimals() ) {
            if (animal.isDead()) {
                map.kill(animal, day);
            }
        }

        map.getAnimals().removeIf(Animal::isDead);
    }

    private void moveAnimals() {
        this.map.move();
    }

    private void consumption() {
        Map<Vector2d, Plant> plantMap = map.getPlantMap();
        Map<Vector2d, TreeSet<Animal>> animalMap = map.getAnimalMap();

        for (Map.Entry<Vector2d, TreeSet<Animal>> entry : animalMap.entrySet()) {
            Vector2d position = entry.getKey();
            Animal animal = entry.getValue().first();

            if ( plantMap.containsKey(position) ) {
                map.consume(
                    animal,
                    plantMap.get(position)
                );
            }
        }
    }


    private void multiplyAnimals() {

        ArrayList<Vector2d> positions = new ArrayList<>(map.getAnimalMap().keySet());

        for ( Vector2d position : positions ) {
            map.multiplyAt(position, day);
        }
    }

    private void renewPlants() {
        this.map.spawnPlants(false);
    }

    private void simulateDay() {
        sunRise();
        clearCorpse();
        moveAnimals();
        consumption();
        multiplyAnimals();

        try {
            Thread.sleep(this.config.refreshTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.signal();
        renewPlants();
        day += 1;
        System.gc();
    }

    //// INTERFACE ////

    /// ENGINE ///

    @Override
    public void run() {
        while (map.getAnimals().size() > 0)
            simulateDay();

        System.out.println("[engine] simulation ended");
    }

    @Override
    public IWorldMap getWorldMap() {
        return map;
    }

    @Override
    public IMapBoundary getMapBoundary() {
        return map.getMapBoundary();
    }

    @Override
    public ArrayList<AnimalStats> statsAt(Vector2d position) {
        return map.statsAt(position, day);
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
        for (IObserver<IEngine> observer : observers )
            observer.signal(this);
    }
}
