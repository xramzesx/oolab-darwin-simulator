package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface IWorldMap {

    //// CONTROLS ///

    void place( IMapElement mapElement, Vector2d prevPosition );
    void move();

    void spawnPlants();

    //// UTILS ////

    ArrayList<IMapElement> objectsAt( Vector2d position );

    //// GETTERS ////

    IMapBoundary getMapBoundary();
    ArrayList<IObservable> getObservables();

    Map<Vector2d, IMapElement> getObjects();

    Set<Vector2d> getGreenArea();
    Set<Vector2d> getNonGreenArea();

    ArrayList<Animal> getAnimals();
    ArrayList<Plant> getPlants();
}
