package oolab.darwin;

import oolab.darwin.boundaries.EarthBoundary;
import oolab.darwin.boundaries.HellishBoundary;
import oolab.darwin.engines.SimulationEngine;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.maps.ToxicMap;
import oolab.darwin.maps.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SimulationEngineTest {

    private ArrayList<Vector2d> generateAnimalPositions(Config config,IMapBoundary mapBoundary) {
        HashSet<Vector2d> positions = new HashSet<>();

        while ( positions.size() < config.initialAnimalQuantity ) {
            positions.add(
                    Utils.getRandomVector2d(
                            mapBoundary.lowerLeft(),
                            mapBoundary.upperRight()
                    )
            );
        }

        return new ArrayList<>(positions);
    }


    @Test
    void simulationEngine(){
        Config config = new Config();

        config.mapWidth=30;
        config.mapHeight=30;

        config.initialPlantQuantity = 10;
        config.initialAnimalQuantity = 5;
        config.initialAnimalEnergy = 10;

        config.stuffedEnergy = 5;
        config.multiplicationEnergy = 10;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.HELLISH;
        config.mapVariant = MapVariant.TOXIC;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;


        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };


        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        ArrayList<Vector2d> animalPositions = new ArrayList<>(Arrays.asList(new Vector2d(0,0), new Vector2d(1,0), new Vector2d(0,1)));

        System.out.println(config.initialAnimalQuantity);
        System.out.println(animalPositions);

        System.out.println(config.mapWidth);
        System.out.println(config.mapHeight);



        IEngine engine = new SimulationEngine(config, worldMap, animalPositions, new ArrayList<>());

        //// START SIMULATION ////

        engine.run();
    }
}
