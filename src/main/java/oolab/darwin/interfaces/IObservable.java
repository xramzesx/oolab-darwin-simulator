package oolab.darwin.interfaces;

public interface IObservable {
    void subscribe( IObserver observer );
    void unsubscribe( IObserver observer );
    void signal();
}
