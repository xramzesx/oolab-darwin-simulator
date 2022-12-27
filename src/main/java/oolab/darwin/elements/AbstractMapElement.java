package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IPositionObserver;

import java.util.ArrayList;

public abstract class AbstractMapElement implements IMapElement {
    public int energy = 0;

    public Vector2d position;
    protected ArrayList<IPositionObserver> observers = new ArrayList<>();

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public void subscribe(IPositionObserver observer) {
        observers.add(observer);
        observer.positionChanged(null, this.position);
    }

    @Override
    public void unsubscribe(IPositionObserver observer) {
        observer.positionChanged(this.position, null);
        observers.remove(observer);
    }

    @Override
    public void signal(){

        /// TODO: add better observer api

        for ( IPositionObserver observer : observers ) {
            // observer.positionChanged();
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }
}
