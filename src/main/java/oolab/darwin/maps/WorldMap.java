package oolab.darwin.maps;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class WorldMap implements IWorldMap {

    //// OBSERVABLES ////

    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<Plant> plants = new ArrayList<>();



    //// INTERFACE ////

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(IMapElement element) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public boolean objectAt(Vector2d position) {
        return false;
    }

    @Override
    public ArrayList<IPositionObservable> getObservables() {
        return null;
    }
}
