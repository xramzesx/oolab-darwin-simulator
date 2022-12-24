package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObserver;

public class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    public Vector2d getPosition() {
        return position;
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
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
