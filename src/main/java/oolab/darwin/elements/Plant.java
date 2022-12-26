package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObserver;

public class Plant extends AbstractMapElement {
    public int energy;
    public Vector2d position;


    public Plant( int energy ){
        this.energy = energy;
    }
}
