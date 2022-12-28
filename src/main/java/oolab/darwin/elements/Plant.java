package oolab.darwin.elements;

import oolab.darwin.Config;
import oolab.darwin.Vector2d;

public class Plant extends AbstractMapElement {
    public int energy;

    public Plant(Config config, Vector2d position){
        this.energy = config.plantEnergy;
        this.position = position;
    }
}
