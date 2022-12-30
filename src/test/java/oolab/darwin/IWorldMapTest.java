package oolab.darwin;

import oolab.darwin.boundaries.EarthBoundary;
import oolab.darwin.boundaries.HellishBoundary;
import oolab.darwin.elements.Animal;
import oolab.darwin.enums.*;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.maps.ToxicMap;
import oolab.darwin.maps.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IWorldMapTest {

    @Test
    void toxicMapTest() {
        Config config = new Config();

        config.mapWidth=30;
        config.mapHeight=30;

        config.initialPlantQuantity = 0;
        config.initialAnimalQuantity = 1;
        config.initialAnimalEnergy = 100;

        config.stuffedEnergy = 5;
        config.multiplicationEnergy = 10;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.EARTH;
        config.mapVariant = MapVariant.TOXIC;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 0;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;

        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };


        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        //// SINGLE KILL AFFECTION ////

        Animal singleAnimal = new Animal(config,worldMap, Genome.generate(config), new Vector2d(1,1));
        worldMap.kill(singleAnimal, 0);
        worldMap.spawnPlants(false);
        assertEquals(1, worldMap.getNonGreenArea().size());

        //// GENERATE KILLS ////

        for ( int y = 0; y < config.mapHeight * 0.4; y++) {
            for ( int x = 0; x < config.mapWidth; x++){
                Animal animal = new Animal(config,worldMap, Genome.generate(config), new Vector2d(x,y));
                worldMap.kill(animal, 0);
            }
        }

        for (int y = (int) (config.mapHeight * 0.6); y < config.mapHeight; y++) {
            for ( int x = 0; x < config.mapWidth; x++) {
                Animal animal = new Animal(config,worldMap, Genome.generate(config), new Vector2d(x,y));
                worldMap.kill(animal, 0);
            }
        }

        //// REFRESH MAPS ////

        worldMap.spawnPlants(false);

        //// CHECK ////

        for ( int y = 0; y < config.mapHeight * 0.4; y++) {
            for ( int x = 0; x < config.mapWidth; x++){
                assert( worldMap.getNonGreenArea().contains(new Vector2d(x,y)) );
            }
        }

        for (int y = (int) (config.mapHeight * 0.4); y < config.mapHeight * 0.6; y++) {
            for ( int x = 0; x < config.mapWidth; x++){
                assert( worldMap.getGreenArea().contains(new Vector2d(x,y)) );
            }
        }

        for (int y = (int) (config.mapHeight * 0.6); y < config.mapHeight; y++) {
            for ( int x = 0; x < config.mapWidth; x++)
                assert( worldMap.getNonGreenArea().contains(new Vector2d(x,y)) );
        }


    }

    @Test
    void WorldMapTest() {
        Config config = new Config();

        config.mapWidth=30;
        config.mapHeight=30;

        config.initialPlantQuantity = 0;
        config.initialAnimalQuantity = 1;
        config.initialAnimalEnergy = 100;

        config.stuffedEnergy = 5;
        config.multiplicationEnergy = 10;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.EARTH;
        config.mapVariant = MapVariant.NORMAL;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 4;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;

        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };


        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        //// CHECKS ////

        for ( int y = 0; y < config.mapHeight * 0.4; y++) {
            for ( int x = 0; x < config.mapWidth; x++){
                assert( worldMap.getNonGreenArea().contains(new Vector2d(x,y)) );
            }
        }

        for (int y = (int) (config.mapHeight * 0.4); y < config.mapHeight * 0.6; y++) {
            for ( int x = 0; x < config.mapWidth; x++){
                assert( worldMap.getGreenArea().contains(new Vector2d(x,y)) );
            }
        }

        for (int y = (int) (config.mapHeight * 0.6); y < config.mapHeight; y++) {
            for ( int x = 0; x < config.mapWidth; x++)
                assert( worldMap.getNonGreenArea().contains(new Vector2d(x,y)) );
        }

    }


    @Test
    void placeTest () {

        Config config = new Config();

        config.mapWidth=30;
        config.mapHeight=30;

        config.initialPlantQuantity = 0;
        config.initialAnimalQuantity = 1;
        config.initialAnimalEnergy = 100;

        config.stuffedEnergy = 5;
        config.multiplicationEnergy = 10;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.EARTH;
        config.mapVariant = MapVariant.NORMAL;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 4;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;

        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };

        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        ArrayList<Integer> occurrences = new ArrayList<Integer>(
            Arrays.asList(1, 10, 20, 100, 200, 1000)
        );

        for (int i = 0; i < occurrences.size(); i++ ) {
            Vector2d position = new Vector2d(i,i);
            for (int j = 0; j < occurrences.get(i); j++ ) {
                Animal animal = new Animal(config,worldMap, Genome.generate(config), position);
                worldMap.place(animal, null);
            }

            assertEquals(occurrences.get(i), worldMap.getAnimalMap().get(position).size());
        }
    }



    @Test
    void statsAtTest () {

        Config config = new Config();

        config.mapWidth=30;
        config.mapHeight=30;

        config.initialPlantQuantity = 0;
        config.initialAnimalQuantity = 1;
        config.initialAnimalEnergy = 100;

        config.stuffedEnergy = 5;
        config.multiplicationEnergy = 10;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.EARTH;
        config.mapVariant = MapVariant.NORMAL;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 4;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;

        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };

        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        ArrayList<Integer> occurrences = new ArrayList<Integer>(
                Arrays.asList(1, 10, 20, 100, 200, 1000)
        );

        for (int i = 0; i < occurrences.size(); i++ ) {
            Vector2d position = new Vector2d(i,i);
            for (int j = 0; j < occurrences.get(i); j++ ) {
                Animal animal = new Animal(config,worldMap, Genome.generate(config), position);
                worldMap.place(animal, null);

                if (Utils.drawResult(50))
                    worldMap.kill(animal, 0);
            }

            assertEquals(occurrences.get(i), worldMap.statsAt(position, 0).size());
        }
    }
}
