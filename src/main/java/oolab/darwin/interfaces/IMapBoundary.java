package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;

public interface IMapBoundary extends IPositionObserver {
    Vector2d lowerLeft();
    Vector2d upperRight();

    void onBorder(Animal animal, Border border );
}
