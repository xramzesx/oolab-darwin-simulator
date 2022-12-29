package oolab.darwin;

import oolab.darwin.boundaries.EarthBoundary;
import oolab.darwin.boundaries.HellishBoundary;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.engines.SimulationEngine;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IMapElement;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.maps.ToxicMap;
import oolab.darwin.maps.WorldMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

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


    private IEngine generateEngine(Config config, ArrayList<Vector2d> animalPositions) {
        IMapBoundary mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary(config);
            case HELLISH -> new HellishBoundary(config);
        };


        IWorldMap worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        return new SimulationEngine(config, worldMap, animalPositions, new ArrayList<>());
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
        config.mapVariant = MapVariant.NORMAL;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 4;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;
        

        ArrayList<Vector2d> animalPositions = new ArrayList<>(Arrays.asList(new Vector2d(0,0), new Vector2d(1,0), new Vector2d(0,1)));

        System.out.println(config.initialAnimalQuantity);
        System.out.println(animalPositions);

        System.out.println(config.mapWidth);
        System.out.println(config.mapHeight);



        IEngine engine = generateEngine(config, animalPositions);

        //// START SIMULATION ////

        engine.run();
    }

    @Test
    void statsAtTest() {

        Config config = new Config();

        config.mapWidth=2;
        config.mapHeight=2;

        config.initialPlantQuantity = 0;
        config.initialAnimalQuantity = 5;
        config.initialAnimalEnergy = 0;

        config.stuffedEnergy = 50;
        config.multiplicationEnergy = 100;
        config.genomeLength = 5;

        config.boundaryVariant = BoundaryVariant.HELLISH;
        config.mapVariant = MapVariant.NORMAL;
        config.mutationVariant = MutationVariant.RANDOMIZED;
        config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

        config.refreshTime = 0;
        config.plantsPerDay = 0;
        config.plantEnergy = 2;
        config.minMutationQuantity = 0;
        config.maxMutationQuantity = 4;

        ArrayList<Vector2d> animalPositions =
                new ArrayList<>(Arrays.asList(
                        new Vector2d(0,0),
                        new Vector2d(0,0),
                        new Vector2d(1,0),
                        new Vector2d(0,1),
                        new Vector2d(1,1),
                        new Vector2d(1,1),
                        new Vector2d(1,1)
                ));

        IEngine engine = generateEngine(config, animalPositions);

        //// ALIVE ANIMALS ////

        assertEquals( 2, engine.getWorldMap().objectsAt(new Vector2d(0, 0)).size() );
        assertEquals( 1, engine.getWorldMap().objectsAt(new Vector2d(1, 0)).size() );
        assertEquals( 1, engine.getWorldMap().objectsAt(new Vector2d(0, 1)).size() );
        assertEquals( 3, engine.getWorldMap().objectsAt(new Vector2d(1, 1)).size() );

        assertEquals( 2, engine.statsAt(new Vector2d(0,0)).size() );
        assertEquals( 1, engine.statsAt(new Vector2d(1,0)).size() );
        assertEquals( 1, engine.statsAt(new Vector2d(0,1)).size() );
        assertEquals( 3, engine.statsAt(new Vector2d(1,1)).size() );

        engine.run();

        //// DEATH ANIMALS ////


        assertEquals( 0, engine.getWorldMap().objectsAt(new Vector2d(0, 0)).size() );
        assertEquals( 0, engine.getWorldMap().objectsAt(new Vector2d(1, 0)).size() );
        assertEquals( 0, engine.getWorldMap().objectsAt(new Vector2d(0, 1)).size() );
        assertEquals( 0, engine.getWorldMap().objectsAt(new Vector2d(1, 1)).size() );

        assertEquals( 2, engine.statsAt(new Vector2d(0,0)).size() );
        assertEquals( 1, engine.statsAt(new Vector2d(1,0)).size() );
        assertEquals( 1, engine.statsAt(new Vector2d(0,1)).size() );
        assertEquals( 3, engine.statsAt(new Vector2d(1,1)).size() );
    }

}
