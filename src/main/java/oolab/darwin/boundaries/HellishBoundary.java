package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.ArrayList;

public class HellishBoundary extends AbstractBoundary {

    public HellishBoundary(Config config) {
        super(config);
    }

    private void onRollup (Animal animal, Border border) {}
    private void onWall( Animal animal, Border border) { }

    //// INTERFACE ////

    @Override
    public void onBorder(Animal animal, Border border) {
        if ( border == null )
            return;

        animal.position = Utils.getRandomVector2d(
            this.lowerLeft(),
            this.upperRight()
        );
    }

    @Override
    public void onBorders(Animal animal, ArrayList<Border> borders) {
        if (  borders.size() <= 0 )
            return;

        super.onBorders(animal, borders);
        animal.changeEnergy(
            -config.multiplicationEnergy
        );
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}

