package oolab.darwin.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.boundaries.EarthBoundary;
import oolab.darwin.boundaries.HellishBoundary;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.engines.SimulationEngine;
import oolab.darwin.interfaces.IEngine;
import oolab.darwin.interfaces.IMapBoundary;
import oolab.darwin.interfaces.IObserver;
import oolab.darwin.interfaces.IWorldMap;
import oolab.darwin.maps.ToxicMap;
import oolab.darwin.maps.WorldMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SimulationView extends Application implements Runnable, IObserver {
    @FXML
    private GridPane simulationGridPane;
    @FXML
    private Button buttonPause;
    @FXML
    private Label labelAnimals;
    @FXML
    private Label labelPlants;
    private Config config;
    private IMapBoundary mapBoundary;
    private IWorldMap worldMap;
    private IEngine engine;
    private Thread engineThread;
    private ArrayList<Vector2d> animalPositions;
    public boolean isThreadRunning = true;

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



    public void initializeView(Config config) {
        this.config = config;
        simulationGridPane.setStyle("-fx-background-color: #55c233");

        //// PREPARING SIMULATION ////

        mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary   (config);
            case HELLISH -> new HellishBoundary (config);
        };


        worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        animalPositions = generateAnimalPositions();
        this.run();
    }

    public void renderGridPane() {
        int cellWidth = 600 / this.config.mapWidth;
        int cellHeight  = 600 / this.config.mapHeight;

        simulationGridPane.setGridLinesVisible(false);
        simulationGridPane.getColumnConstraints().clear();
        simulationGridPane.getRowConstraints().clear();
        simulationGridPane.getChildren().clear();

        simulationGridPane.setGridLinesVisible(true);
        simulationGridPane.setGridLinesVisible(true);

        Label yxLabel = new Label("");
        simulationGridPane.add(yxLabel, 0, 0, 1, 1);
        simulationGridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        simulationGridPane.getRowConstraints().add(new RowConstraints(cellHeight));

        for (int i = 1; i < this.config.mapWidth; i++) {
            Label label = new Label( "");
            simulationGridPane.add(label, i, 0, 1, 1);
            simulationGridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < this.config.mapHeight; i++) {
            Label label = new Label(  "");
            simulationGridPane.add(label, 0, i, 1, 1);
            simulationGridPane.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }


        ArrayList<Animal> animals = worldMap.getAnimals();
        labelAnimals.setText("Animal count: " + animals.size());
        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i).getPosition().x >= 0 && animals.get(i).getPosition().x < this.config.mapWidth && animals.get(i).getPosition().y >= 0 && animals.get(i).getPosition().y < this.config.mapHeight) {
                Pane animal = new Pane();
                animal.setStyle("-fx-background-color: #964b00");
                simulationGridPane.add(animal,  animals.get(i).getPosition().x, animals.get(i).getPosition().y, 1, 1);
            }
       }

        ArrayList<Plant> plants = worldMap.getPlants();
        labelPlants.setText("Plant count: " + plants.size());
        for (int i = 0; i < plants.size(); i++) {
            if(plants.get(i).getPosition().x >= 0 && plants.get(i).getPosition().y >= 0) {
                Pane plant = new Pane();
                plant.setStyle("-fx-background-color: #55c233");
                simulationGridPane.add(plant,  plants.get(i).getPosition().x, plants.get(i).getPosition().y, 1, 1);
            }
        }

    }

    public void handlePauseClick() {
        if(isThreadRunning) {
            engineThread.suspend(); // TODO change it for more actual version
            isThreadRunning = false;
            buttonPause.setText("Start");
            buttonPause.setStyle("-fx-background-color: #55c233");

        } else {
            engineThread.resume();
            isThreadRunning = true;
            buttonPause.setText("Pause");
            buttonPause.setStyle("-fx-background-color: #ff605C");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void run() {

        //// TODO: add missing config inputs
        config.plantsPerDay = 4;
        config.plantEnergy = 5;

        engine = new SimulationEngine(
            config,
            worldMap,
            animalPositions,
            new ArrayList<>(Arrays.asList(
                this
            ))
        );
        engineThread = new Thread(engine);
        engineThread.start();
    }

    @Override
    public void signal(IEngine engine) {

        Platform.runLater(this::renderGridPane);

        //// example api use: ///

        /// engine.getWorldMap();
        /// engine.getMapBoundary();
    }
}
