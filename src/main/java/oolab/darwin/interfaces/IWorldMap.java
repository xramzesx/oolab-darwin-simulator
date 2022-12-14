package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;

import java.util.ArrayList;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);
    boolean place( IMapElement element );
    boolean isOccupied( Vector2d position );
    boolean objectAt( Vector2d position );

    IMapBoundary mapBoundary = null;

    ArrayList<IPositionObservable> getObservables();
}
