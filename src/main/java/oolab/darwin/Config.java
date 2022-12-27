package oolab.darwin;

import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

public class Config {
    public Integer mapWidth = 30;
    public Integer mapHeight = 30;

    public Integer initialPlantQuantity = 10;
    public Integer initialAnimalQuantity = 5;
    public Integer initialAnimalEnergy = 10;

    public Integer stuffedEnergy = 5;
    public Integer multiplicationEnergy = 10;
    public Integer genomeLength = 5;

    public BoundaryVariant boundaryVariant = BoundaryVariant.HELLISH;
    public MapVariant mapVariant = MapVariant.TOXIC;
    public MutationVariant mutationVariant =  MutationVariant.RANDOMIZED;
    public AnimalBehaviorVariant animalBehaviorVariant =AnimalBehaviorVariant.DEVIATION;
    public Integer refreshTime = 1000;
}
