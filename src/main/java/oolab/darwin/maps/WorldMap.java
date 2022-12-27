package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.interfaces.IMapBoundary;

public class WorldMap extends AbstractWorldMap {

    public WorldMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        super(config, mapBoundary);
    }
}
