package oolab.darwin.interfaces;

import oolab.darwin.Vector2d;

public interface IMapElement extends IPositionObservable {
    Vector2d getPosition();
    int getEnergy();
}
