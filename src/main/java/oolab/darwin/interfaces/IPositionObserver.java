package oolab.darwin.interfaces;
import oolab.darwin.Vector2d;

public interface IPositionObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
