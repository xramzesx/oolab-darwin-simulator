package oolab.darwin.gui;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.boundaries.EarthBoundary;
import oolab.darwin.boundaries.HellishBoundary;
import oolab.darwin.elements.Animal;
import oolab.darwin.engines.SimulationEngine;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.interfaces.*;
import oolab.darwin.maps.ToxicMap;
import oolab.darwin.maps.WorldMap;

import java.util.ArrayList;
import java.util.HashSet;

public class SimulationView implements Runnable, IPositionObserver{
    private final Config config;

    private ArrayList<Vector2d> generateAnimalPositions() {
        HashSet<Vector2d> positions = new HashSet<>();

        while ( positions.size() < config.initialAnimalQuantity ) {
            positions.add(
                Utils.getRandomVector2d(
                    this.mapBoundary.lowerLeft(),
                    this.mapBoundary.upperRight()
                )
            );
        }

        return new ArrayList<>(positions);
    }

    IMapBoundary mapBoundary;
    IWorldMap worldMap;
    IEngine engine;


    public SimulationView(Config config) {
        this.config = config;


        //// PREPARING SIMULATION ////

        mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary   (config);
            case HELLISH -> new HellishBoundary (config);
        };


        worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        worldMap = config.mapVariant == MapVariant.NORMAL
            ? new WorldMap(config, mapBoundary)
            : new ToxicMap(config, mapBoundary);


        ArrayList<Vector2d> animalPositions = generateAnimalPositions();

        System.out.println(config.initialAnimalQuantity);
        System.out.println(animalPositions);

        System.out.println(config.mapWidth);
        System.out.println(config.mapHeight);

//        engine = new SimulationEngine(config, worldMap, animalPositions, this, this);

        //// START SIMULATION ////

        engine.run();

    }

    @Override
    public void run() {
        engine.run();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        //// TODO: do better api
        System.out.println(worldMap.getAnimals());
        System.out.println(worldMap.getPlants());
        System.out.println(worldMap.getObjects());
    }
}
