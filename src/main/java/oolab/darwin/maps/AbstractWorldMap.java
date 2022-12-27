package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.AbstractMapElement;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractWorldMap implements IWorldMap, IPositionObserver {

    //// OBSERVABLES ////

    protected final ArrayList<Animal> animals = new ArrayList<>();
    protected final ArrayList<Plant> plants = new ArrayList<>();
    protected final Map<Vector2d, IMapElement> objects = new HashMap<>();


    //// GLOBALS ////

    protected Config config;
    protected IMapBoundary mapBoundary;

    public AbstractWorldMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        this.config = config;
        this.mapBoundary = mapBoundary;
    }

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
    public boolean place(IMapElement mapElement, Vector2d prevPosition) {
        //// TODO: rewrite this function

        if (prevPosition != null) {

        }

        if (canMoveTo(mapElement.getPosition())) {
            if ( mapElement instanceof Animal )
                animals.add((Animal) mapElement);
            this.objects.put(mapElement.getPosition(), mapElement);
            return true;
        }

        return false;
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
    public void move() {
        for ( Animal animal : animals )
            animal.move();

        System.out.println(animals);
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
    public IMapBoundary getMapBoundary() {
        return mapBoundary;
    }

    @Override
    public ArrayList<IPositionObservable> getObservables() {
        return null;
    }

    @Override
    public Map<Vector2d, IMapElement> getObjects() {
        return objects;
    }

    @Override
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public ArrayList<Plant> getPlants() {
        return plants;
    }
}
