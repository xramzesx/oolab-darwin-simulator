package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

public class HellishBoundary extends AbstractBoundary {

    public HellishBoundary(Config config) {
        super(config);
    }

    private void onRollup (Animal animal, Border border) {}
    private void onWall( Animal animal, Border border) { }

    //// INTERFACE ////

    @Override
    public void onBorder(Animal animal, Border border) {

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}

