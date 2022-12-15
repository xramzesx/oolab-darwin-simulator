package oolab.darwin;

import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

public class Config {
    public Integer mapWidth;
    public Integer mapHeight;

    public Integer initialPlantQuantity;
    public Integer initialAnimalQuantity;
    public Integer initialAnimalEnergy;

    public Integer stuffedEnergy;
    public Integer multiplicationEnergy;
    public Integer genomLength;

    public MapVariant mapVariant;
    public MutationVariant mutationVariant;
    public AnimalBehaviorVariant animalBehaviorVariant;
}
