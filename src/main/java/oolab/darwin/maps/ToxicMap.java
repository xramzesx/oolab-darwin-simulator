package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.HashSet;
import java.util.Set;

public class ToxicMap extends AbstractWorldMap {

    private void prepareArea() {

    }

    @Override
    public void spawnPlants(boolean isInitial) {
        prepareArea();
        super.spawnPlants(isInitial);
    }

    public ToxicMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        super(config, mapBoundary);
    }

    @Override
    public Set<Vector2d> getGreenArea() {
        return new HashSet<>();
    }

    @Override
    public Set<Vector2d> getNonGreenArea() {
        return new HashSet<>();
    }
}
