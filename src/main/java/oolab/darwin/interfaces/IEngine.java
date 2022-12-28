package oolab.darwin.interfaces;

public interface IEngine extends Runnable, IObservable{
    void run();

    //// GETTERS ///
    IWorldMap getWorldMap();
    IMapBoundary getMapBoundary();

}
