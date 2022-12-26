package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IPositionObservable;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;

public class ToxicMap extends AbstractWorldMap {
    public ToxicMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        super(config, mapBoundary);
    }
}
