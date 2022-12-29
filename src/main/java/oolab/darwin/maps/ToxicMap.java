package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ToxicMap extends AbstractWorldMap {

    private final Set<Vector2d> greenArea = new HashSet<>();
    private final Set<Vector2d> nonGreenArea = new HashSet<>();

    private void prepareArea() {
        greenArea.clear();
        nonGreenArea.clear();

        ArrayList<Vector2d> deathFields = getSortedDeathFields();

        int nonGreenAreaLength = (int) Math.min( deathFields.size(), config.mapWidth * config.mapHeight * 0.8 );

        nonGreenArea.addAll( deathFields.subList(0, nonGreenAreaLength ) );

        for (int y = 0; y < config.mapHeight; y++ ) {
            for (int x = 0; x < config.mapWidth; x++) {
                Vector2d position = new Vector2d(x, y);
                if (!nonGreenArea.contains(position))
                    greenArea.add(position);
            }
        }
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
        prepareArea();
    }

    @Override
    public Set<Vector2d> getGreenArea() {
        return greenArea;
    }

    @Override
    public Set<Vector2d> getNonGreenArea() {
        return nonGreenArea;
    }
}
