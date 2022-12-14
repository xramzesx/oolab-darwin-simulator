package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObserver;

public class Plant implements IMapElement {
    public int energy;
    public Vector2d position;


    public Plant( int energy ){
        this.energy = energy;
    }

    @Override
    public void subscribe(IPositionObserver observer) {

    }

    @Override
    public void unsubscribe(IPositionObserver observer) {

    }

    @Override
    public void notify(IPositionObserver observer) {

    }
}
