package oolab.darwin.maps;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class ToxicMap implements IWorldMap {

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
