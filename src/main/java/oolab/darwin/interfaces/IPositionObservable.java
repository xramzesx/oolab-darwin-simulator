package oolab.darwin.interfaces;

public interface IPositionObservable {
    void subscribe( IPositionObserver observer );
    void unsubscribe(IPositionObserver observer );
    void notify( IPositionObserver observer );
}
