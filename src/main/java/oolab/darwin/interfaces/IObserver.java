package oolab.darwin.interfaces;
import oolab.darwin.Vector2d;

public interface IObserver <T> {
    void signal(T object);
}
