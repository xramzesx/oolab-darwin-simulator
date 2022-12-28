package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;

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

        if (border == Border.BOTTOM || border == Border.TOP) {
            animal.direction = animal.direction.flipHorizontal();

            animal.position = new Vector2d(
                animal.position.x,
                    border == Border.BOTTOM
                    ? lowerLeft().y
                    : upperRight().y
            );

        } else {
            animal.position = new Vector2d(
                    (animal.position.x + config.mapWidth) % config.mapWidth,
                    animal.position.y
            );
        }

    }

}
