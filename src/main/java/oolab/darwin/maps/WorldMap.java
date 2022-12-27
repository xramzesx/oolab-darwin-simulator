package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class WorldMap extends AbstractWorldMap {

    public WorldMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        super(config, mapBoundary);
    }
}
