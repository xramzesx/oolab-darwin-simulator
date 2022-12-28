package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldMap extends AbstractWorldMap {

    private final Set<Vector2d> greenArea = new HashSet<>();
    private final Set<Vector2d> nonGreenArea = new HashSet<>();

    private void prepareArea() {
        greenArea.clear();
        nonGreenArea.clear();

        for (
            int y = (int) (config.mapHeight * 0.4);
            y < (int) (config.mapHeight * 0.6);
            y++
        ) {
            for (int x = 0; x < config.mapWidth; x++ ){
                greenArea.add(new Vector2d(x, y));
            }
        }

        for ( int y = 0; y < config.mapHeight; y++ ){
            for (int x = 0; x < config.mapWidth; x++ ) {
                Vector2d position = new Vector2d(x, y);
                if ( !greenArea.contains(position))
                    nonGreenArea.add(position);
            }
        }
    }

    public WorldMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        super(config, mapBoundary);

        prepareArea();
    }


    private int totalFields() {
        return config.mapWidth * config.mapHeight;
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
