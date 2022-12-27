package oolab.darwin.boundaries;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Border;
import oolab.darwin.interfaces.IMapBoundary;

import java.util.ArrayList;

public abstract class AbstractBoundary implements IMapBoundary {
    protected final Config config;

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

    @Override
    public Vector2d lowerLeft() {
        return this.startBorder;
    };

    @Override
    public Vector2d upperRight() {
        return this.endBorder;
    }

    @Override
    public void checkPosition(Animal animal) {
        ArrayList<Border> borders = new ArrayList<>();

        if (animal.position.y < this.lowerLeft().y )
            borders.add(Border.BOTTOM);
        if ( animal.position.y > this.upperRight().y )
            borders.add(Border.TOP);
        if ( animal.position.x < this.lowerLeft().x )
            borders.add(Border.LEFT);
        if ( animal.position.x > this.upperRight().x )
            borders.add(Border.RIGHT);

        onBorders(animal, borders);
    }

    @Override
    public void onBorders( Animal animal, ArrayList<Border> borders ) {
        for ( Border border: borders )
            onBorder(animal, border);
    }

}
