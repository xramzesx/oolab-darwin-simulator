package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

public class EarthBoundary extends AbstractBoundary {
    public EarthBoundary(Config config) {
        super(config);
    }

    private void onBorderHit(Animal animal) {

    }

    //// INTERFACE ////

    @Override
    public void onBorder(Animal animal, Border border) {

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
