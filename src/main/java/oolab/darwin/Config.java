package oolab.darwin;

import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

public class Config {
    //// GENERAL ///
    public Integer mapWidth;
    public Integer mapHeight;

    //// INITIAL ////
    public Integer initialPlantQuantity;
    public Integer initialAnimalQuantity;
    public Integer initialAnimalEnergy;

    //// PLANT ////
    public Integer plantEnergy;
    public Integer plantsPerDay;

    //// ANIMAL ////
    public Integer stuffedEnergy;
    public Integer multiplicationEnergy;
    public Integer genomeLength;
    public Integer minMutationQuantity;
    public Integer maxMutationQuantity;

    //// RADIOS ////
    public BoundaryVariant boundaryVariant;
    public MapVariant mapVariant;
    public MutationVariant mutationVariant;
    public AnimalBehaviorVariant animalBehaviorVariant;

    //// OTHER ////
    public Integer refreshTime;
}
