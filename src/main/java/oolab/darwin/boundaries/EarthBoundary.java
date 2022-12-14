package oolab.darwin.boundaries;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

public class EarthBoundary implements IMapBoundary {
    private void onBorderHit(Animal animal) {

    }

    @Override
    public Vector2d lowerLeft() {
        return null;
    }

    @Override
    public Vector2d upperRight() {
        return null;
    }

    @Override
    public void onBorder(Animal animal, Border border) {

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
