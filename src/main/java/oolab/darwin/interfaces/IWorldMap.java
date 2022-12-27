package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;

import java.util.ArrayList;
import java.util.Map;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);
    boolean place( IMapElement mapElement, Vector2d prevPosition );
    boolean isOccupied(Vector2d position );
    Object objectAt( Vector2d position );

    IMapBoundary getMapBoundary();

    void move();

    ArrayList<IPositionObservable> getObservables();

    Map<Vector2d, IMapElement> getObjects();

    ArrayList<Animal> getAnimals();
    ArrayList<Plant> getPlants();
}
