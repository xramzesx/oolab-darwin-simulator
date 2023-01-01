package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.stats.AnimalStats;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public interface IWorldMap {

    //// CONTROLS ///

    void place( IMapElement mapElement, Vector2d prevPosition );
    void move();

    void spawnPlants(boolean isInitial);

    void consume( Animal animal, Plant plant );
    void multiplyAt (Vector2d position, int birthDate);
    void kill (Animal animal, int deathDate);

    //// UTILS ////

    ArrayList<IMapElement> objectsAt( Vector2d position );
    ArrayList<AnimalStats> statsAt (Vector2d position, int currentDay);

    //// GETTERS ////

    Integer getTotalFields();

    Double getAvgLifeSpan();

    IMapBoundary getMapBoundary();
    ArrayList<IObservable> getObservables();

    Map<Vector2d, IMapElement> getObjects();

    Set<Vector2d> getGreenArea();
    Set<Vector2d> getNonGreenArea();

    CopyOnWriteArrayList<Animal> getAnimals();
    CopyOnWriteArrayList<Plant> getPlants();

    Map<Vector2d, TreeSet<Animal>> getAnimalMap();
    Map<Vector2d, Plant> getPlantMap();
}
