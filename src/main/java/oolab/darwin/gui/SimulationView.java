package oolab.darwin.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import oolab.darwin.CSVWriter;
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
import oolab.darwin.stats.AnimalStats;
import oolab.darwin.stats.EngineStats;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SimulationView extends Application implements Runnable, IObserver <IEngine> {
    @FXML
    private GridPane simulationGridPane;
    @FXML
    private Button buttonPause;
    @FXML
    private Label labelAnimals;
    @FXML
    private Label labelPlants;
    @FXML
    private Label labelDays;
    @FXML
    private Label labelAnimalInfo;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private Label labelAvgAge;
    @FXML
    private Label labelAvgEnergy;
    @FXML
    private Label labelEmptySpaces;
    @FXML
    private Label labelMostPopularGenotype;
    private Config config;
    private IMapBoundary mapBoundary;
    private IWorldMap worldMap;
    private SimulationEngine engine;
    private Thread engineThread;
    private ArrayList<Vector2d> animalPositions;
    public boolean isThreadRunning = true;
    public XYChart.Series animalSeries = new XYChart.Series();
    public XYChart.Series plantSeries = new XYChart.Series();
    public Animal selectedAnimal;
    public Integer selectedAnimalId = -1;
    int cellWidth = 0;
    int cellHeight= 0;
    int widowNumber = 0;
    CSVWriter fileWriter;

    public ArrayList<Vector2d> generateAnimalPositions() {
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

    public void closeWindow() {
        if(this.config.shouldSaveDataToCSV == 1) {
            openFile("simulationStats" + widowNumber +".csv");
        }
        this.engine.stopThread();
    }

    public void openFile(String fileName) {
        try
        {
            File file_open = new File("./" + fileName);
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Desktop Support Not Present in the system.");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file_open.exists())
                desktop.open(file_open);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initializeView(Config config, Integer widowNumber) {
        /// LINE CHART SETTINGS ///
        lineChart.setCreateSymbols(false);
        animalSeries.setName("Animal count");
        plantSeries.setName("Plant count");
        Platform.runLater(() -> lineChart.getData().addAll(animalSeries, plantSeries));

        this.config = config;
        simulationGridPane.setStyle("-fx-background-color: #5fc314");

        if(this.config.shouldSaveDataToCSV > 0) {
            this.widowNumber = widowNumber;
            fileWriter = new CSVWriter("simulationStats" + widowNumber +".csv");
            fileWriter.clearFile();
            fileWriter.saveRecord("Day,Animal quantity,Plants quantity,Free fields quantity,Most popular genotype,Avg animal energy,Avg life span"); // title
        }

        cellWidth = 600 / this.config.mapWidth;
        cellHeight  = 600 / this.config.mapHeight;

        //// PREPARING SIMULATION ////

        mapBoundary = switch (config.boundaryVariant) {
            case EARTH ->   new EarthBoundary   (config);
            case HELLISH -> new HellishBoundary (config);
        };


        worldMap = switch (config.mapVariant) {
            case NORMAL ->  new WorldMap(config, mapBoundary);
            case TOXIC ->   new ToxicMap(config, mapBoundary);
        };

        generateMapArea();
        animalPositions = generateAnimalPositions();
        this.run();
    }

    private void generateMapArea() {
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
    }

    private void generatePlants() {
        try {
            ArrayList<Plant> plants = worldMap.getPlants();
            Platform.runLater(() -> plantSeries.getData().add(new XYChart.Data(this.engine.day + "", plants.size())));

            for (int i = 0; i < plants.size(); i++) {
                Plant currentPlant = plants.get(i);
                if(currentPlant.getPosition().x >= 0 && currentPlant.getPosition().x < plants.size() && currentPlant.getPosition().y >= 0 && currentPlant.getPosition().y < plants.size()) {
                    Pane plant = new Pane();
                    plant.setStyle("-fx-background-color: #1f6d04");
                    simulationGridPane.add(plant,  plants.get(i).getPosition().x, plants.get(i).getPosition().y, 1, 1);
                }
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private void generateAnimals() {
        try {
            ArrayList<Animal> animals = worldMap.getAnimals();
            Platform.runLater(() -> animalSeries.getData().add(new XYChart.Data(this.engine.day + "", animals.size())));

            for (int i = 0; i < animals.size(); i++) {
                Animal currentAnimal = animals.get(i);
                if(currentAnimal.getPosition().x >= 0 && currentAnimal.getPosition().x < this.config.mapWidth && currentAnimal.getPosition().y >= 0 && currentAnimal.getPosition().y < this.config.mapHeight) {
                    Pane animal = new Pane();
                    animal.setId(i + "");
                    animal.setOnMouseClicked(event -> {
                        selectedAnimalId = Integer.parseInt(animal.getId());
                        selectedAnimal = animals.get(selectedAnimalId);
                        showSpecificInformation();
                        renderGridPane();
                    });

                    animal.setStyle("-fx-background-color: " + ((!isThreadRunning && currentAnimal.getGenomes().equals(this.engine.getStats().mostPopularGenotype)) ?  "blue" : getAnimalColor(animals.get(i).energy)) + ";" +
                            "-fx-border-color:" + (selectedAnimalId == i ? "red" : "none") + ";");

                    simulationGridPane.add(animal,  animals.get(i).getPosition().x, animals.get(i).getPosition().y, 1, 1);
                }
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }


    private void generateEngineStats() {
        labelDays.setText("Day: " + this.engine.day);
        labelAnimals.setText("Animal count: " +this.engine.getStats().animalsQuantity);
        labelPlants.setText("Plant count: " + this.engine.getStats().plantsQuantity);
        labelEmptySpaces.setText("Empty spaces count: " + this.engine.getStats().freeFieldsQuantity);
        labelMostPopularGenotype.setText(this.engine.getStats().mostPopularGenotype + "");
        labelAvgEnergy.setText("Average animal energy: " +  Math.round(this.engine.getStats().avgAnimalEnergy * 100.0) / 100.0);
        labelAvgAge.setText("Average animal age: " +  Math.round(this.engine.getStats().avgLifeSpan * 100.0) / 100.0);
    }

    public void renderGridPane() {
        simulationGridPane.setGridLinesVisible(false);
        simulationGridPane.getChildren().clear();
        simulationGridPane.setGridLinesVisible(true);

        generatePlants();
        generateAnimals();
        generateEngineStats();
        showSpecificInformation();

        // Add new data to line chart
        Platform.runLater(() -> {
            if (animalSeries.getData().size() > 10) {
                animalSeries.getData().remove(0);
                plantSeries.getData().remove(0);
            }
        });
    }

    public String getAnimalColor(Integer energy) {
      if(energy < 2) {
          return "#d8cbc4";
      } else if (energy < 5) {
          return "#a08679";
      } else if (energy < 10) {
          return "#765341";
      } else {
          return "#4c3228";
      }
    }

    private void showSpecificInformation() {
        if(selectedAnimal != null) {
            AnimalStats stats = selectedAnimal.getStats(this.engine.day);
            labelAnimalInfo.setText("Is alive: " +(stats.deathDate < 0 ? "yes" : "RIP") +
                "\nGenomes: " + stats.genomes +
                "\nCurrent genome: " + stats.currentGenome +
                "\nEnergy: " + stats.energy  +
                "\nPlants eated: " + stats.eatenPlants +
                "\nChildren: " + stats.children +
                "\nDays survived: " + stats.getAge() +
                "\nBirth day: " + stats.birthDate +
                "\nDied on day: " + (stats.deathDate < 0 ? "-" : stats.deathDate));
        }
    }

    public void handlePauseClick() {
        if(isThreadRunning) {
            this.engine.pauseThread();
            isThreadRunning = false;
            buttonPause.setText("Start");
            buttonPause.setStyle("-fx-background-color: #55c233");
            renderGridPane();
        } else {
            this.engine.resumeThread();
            isThreadRunning = true;
            buttonPause.setText("Pause");
            buttonPause.setStyle("-fx-background-color: #ff605C");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {}

    @Override
    public void run() {
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
        if(this.config.shouldSaveDataToCSV == 1) {
            EngineStats stats = this.engine.getStats();
            fileWriter.saveRecord(this.engine.day + "," + stats.animalsQuantity + "," + stats.plantsQuantity + "," + stats.freeFieldsQuantity + "," + fileWriter.arrayToWritableString(stats.mostPopularGenotype) + "," + stats.avgAnimalEnergy + "," + stats.avgLifeSpan);
        }
    }
}
