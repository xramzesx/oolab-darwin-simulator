package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.interfaces.IMapBoundary;

public abstract class AbstractBoundary implements IMapBoundary {
    private final Config config;

    private final Vector2d startBorder;
    private final Vector2d endBorder;

    public AbstractBoundary(Config config){
        this.config = config;
        this.startBorder = new Vector2d(0,0);
        this.endBorder = new Vector2d(
            this.config.mapWidth - 1,
            this.config.mapHeight - 1
        );
    }

    public Vector2d lowerLeft() {
        return this.startBorder;
    };

    public Vector2d upperRight() {
        return this.endBorder;
    }
}
