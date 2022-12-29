package oolab.darwin.stats;

import oolab.darwin.elements.Animal;
import oolab.darwin.enums.Genome;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineStats {

    public final Integer animalsQuantity;
    public final Integer plantsQuantity;

    public final Integer freeFieldsQuantity;

    public final Double avgAnimalEnergy;

    public final Double avgLifeSpan;

    public final ArrayList<Genome> mostPopularGenotype;
    public EngineStats (IEngine engine) {
        IWorldMap map = engine.getWorldMap();

        animalsQuantity = map
            .getAnimals()
            .size();

        plantsQuantity = map
            .getPlants()
            .size();

        freeFieldsQuantity = map.getTotalFields() - map.getAnimalMap().size();

        avgAnimalEnergy = map
            .getAnimals()
            .stream()
            .map(animal -> animal.energy)
            .mapToDouble( d -> d )
            .average()
            .orElse(0);

        avgLifeSpan = map.getAvgLifeSpan();


        if ( animalsQuantity == 0) {
            mostPopularGenotype = new ArrayList<>();
            return;
        }

        Map<List<Genome>, Integer> occurrences = new HashMap<>();

        for (Animal animal : map.getAnimals() ) {
            Integer count = occurrences.getOrDefault(animal.getGenomes(), 0);
            occurrences.put(animal.getGenomes(), count + 1);
        }

        mostPopularGenotype = (ArrayList<Genome>) occurrences
            .entrySet()
            .stream()
            .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
            .orElse(occurrences.entrySet().iterator().next())
            .getKey();

    }
}
