package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.ArrayList;

public class EarthBoundary extends AbstractBoundary {
    public EarthBoundary(Config config) {
        super(config);
    }

    private void onBorderHit(Animal animal) {

    }

    //// INTERFACE ////

    @Override
    public void onBorder(Animal animal, Border border) {
        if ( border == null )
            return;
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
