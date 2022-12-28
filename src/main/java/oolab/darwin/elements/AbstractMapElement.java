package oolab.darwin.elements;

import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapElement;

import java.util.ArrayList;

public abstract class AbstractMapElement implements IMapElement {
    public int energy = 0;

    public Vector2d position;

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
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
