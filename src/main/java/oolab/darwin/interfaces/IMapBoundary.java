package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;

import java.util.ArrayList;

public interface IMapBoundary extends IPositionObserver {
    Vector2d lowerLeft();
    Vector2d upperRight();

    void onBorders(Animal animal, ArrayList<Border> borders);
    void onBorder(Animal animal, Border border );

    void checkPosition(Animal animal);
}
