package oolab.darwin.maps;

import oolab.darwin.Vector2d;
import oolab.darwin.elements.AbstractMapElement;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IPositionObserver;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractWorldMap implements IWorldMap, IPositionObserver {
    protected final Map<Vector2d, IMapElement> objects = new HashMap<>();

    private void setRandomGrassFields(int n) {
        Random generator = new Random();
        int i = 0;
        while(i < n) {
            int x = generator.nextInt(this.mapBoundary.upperRight().x - 1);
            int y = generator.nextInt(this.mapBoundary.upperRight().y - 1);
            Object objectAtThisArea = this.objectAt(new Vector2d(x, y));
            if(!(objectAtThisArea instanceof Plant) && !(objectAtThisArea instanceof Animal)) {
                Vector2d newPosition = new Vector2d(x, y);
                objects.put(newPosition, new Plant(1));
                i += 1;
            }
        }
    }

    @Override
    public boolean place(IMapElement animal) {
        System.out.println(animal.position);
        if (canMoveTo(animal.position)) {
            this.objects.put(animal.position, animal);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.position + " is already taken. You can't moce to this position");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objects.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.objects.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement animal = this.objects.get(oldPosition);
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        if(object instanceof Plant) {
            this.objects.remove(object);
            setRandomGrassFields(1);
        } else if(object instanceof Animal) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<IPositionObservable> getObservables() {
        return null;
    }

}
