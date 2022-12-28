package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.stats.AnimalStats;

import java.util.ArrayList;

public interface IEngine extends Runnable, IObservable{
    void run();

    //// GETTERS ///
    IWorldMap getWorldMap();
    IMapBoundary getMapBoundary();

    //// STATISTICS ////

    ArrayList<AnimalStats> statsAt(Vector2d position);

}
