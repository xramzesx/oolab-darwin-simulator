package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.enums.MutationVariant;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObserver;

public class Animal implements IMapElement {
    public int energy;
    public Vector2d position;
    private MutationVariant mutationVariant;


    //// BEHAVIOR ///

    private void mutate() {

    }

    public void move(Vector2d position) {

    }

    //// INTERFACE ////

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
