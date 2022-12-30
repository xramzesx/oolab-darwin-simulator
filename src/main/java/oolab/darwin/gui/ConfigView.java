package oolab.darwin.gui;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oolab.darwin.Config;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConfigView extends Application {
    @FXML
    private ToggleGroup boundaryType;
    @FXML
    private TextField inputAnimalEnergy;
    @FXML
    private TextField inputAnimalQuantity;
    @FXML
    private TextField inputGenomeLength;
    @FXML
    private TextField inputMapHeight;
    @FXML
    private TextField inputMapWidth;
    @FXML
    private TextField inputMultiplicationEnergy;
    @FXML
    private TextField inputPlantQuantity;
    @FXML
    private TextField inputRefreshTime;
    @FXML
    private TextField inputStuffedEnergy;
    @FXML
    private ToggleGroup mapType;
    @FXML
    private RadioButton radioClassicMap;
    @FXML
    private RadioButton radioEarth;
    @FXML
    private RadioButton radioHellish;
    @FXML
    private RadioButton radioToxicMap;
    @FXML
    private Label labelErrorMessage;
    @FXML
    private RadioButton radioPredestination;
    @FXML
    private RadioButton radioRandomized;
    @FXML
    private RadioButton radioCorrected;
    @FXML
    private RadioButton radioDeviation;
    @FXML
    private ToggleGroup mutationVariant;
    @FXML
    private ToggleGroup behaviourVariant;
    @FXML
    private TextField inputPlantsPerDay;
    @FXML
    private TextField inputMaxMutationQuantity;
    @FXML
    private TextField inputMinMutationQuantity;
    @FXML
    private TextField inputPlantEnergy;
    @FXML
    private CheckBox checkBoxSaveToCSV;

    @FXML
    public void initialize() {
        loadSettingsFromFile(new File("settings.json"), "Could not find file settings.json", false);
    }

    public Integer widowsOpened = 0;

    public void loadSettings(Config config) {
        if(config.mapHeight != null)
            inputMapHeight.setText(config.mapHeight.toString());
        else
            inputMapHeight.setText("");

        if(config.mapWidth != null)
            inputMapWidth.setText(config.mapWidth.toString());
        else
            inputMapWidth.setText("");

        if(config.initialPlantQuantity != null)
            inputPlantQuantity.setText(config.initialPlantQuantity.toString());
        else
            inputPlantQuantity.setText("");

        if(config.initialAnimalQuantity != null)
            inputAnimalQuantity.setText(config.initialAnimalQuantity.toString());
        else
            inputAnimalQuantity.setText("");

        if(config.initialAnimalEnergy != null)
            inputAnimalEnergy.setText(config.initialAnimalEnergy.toString());
        else
            inputAnimalEnergy.setText("");

        if(config.stuffedEnergy != null)
            inputStuffedEnergy.setText(config.stuffedEnergy.toString());
        else
            inputStuffedEnergy.setText("");

        if(config.multiplicationEnergy != null)
            inputMultiplicationEnergy.setText(config.multiplicationEnergy.toString());
        else
            inputMultiplicationEnergy.setText("");

        if(config.genomeLength != null)
            inputGenomeLength.setText(config.genomeLength.toString());
        else
            inputGenomeLength.setText("");

        if(config.multiplicationEnergy != null)
            inputMultiplicationEnergy.setText(config.multiplicationEnergy.toString());
        else
            inputMultiplicationEnergy.setText("");

        if(config.refreshTime != null)
            inputRefreshTime.setText(config.refreshTime.toString());
        else
            inputRefreshTime.setText("");

        if(config.plantsPerDay != null)
            inputPlantsPerDay.setText(config.plantsPerDay.toString());
        else
            inputPlantsPerDay.setText("");

        if(config.plantEnergy != null)
            inputPlantEnergy.setText(config.plantEnergy.toString());
        else
            inputPlantEnergy.setText("");

        if(config.minMutationQuantity != null)
            inputMinMutationQuantity.setText(config.minMutationQuantity.toString());
        else
            inputMinMutationQuantity.setText("");

        if(config.maxMutationQuantity != null)
            inputMaxMutationQuantity.setText(config.maxMutationQuantity.toString());
        else
            inputMaxMutationQuantity.setText("");

        if(config.shouldSaveDataToCSV != null)
            checkBoxSaveToCSV.setSelected(config.shouldSaveDataToCSV == 1);
        else
            checkBoxSaveToCSV.setSelected(false);

        if(config.mapVariant != null && config.mapVariant.equals(MapVariant.NORMAL)) {
            radioClassicMap.setSelected(true);
        } else {
            radioToxicMap.setSelected(true);
        }

        if(config.boundaryVariant != null && config.boundaryVariant.equals(BoundaryVariant.EARTH)) {
            radioEarth.setSelected(true);
        } else {
            radioHellish.setSelected(true);
        }

        if(config.mutationVariant != null && config.mutationVariant.equals(MutationVariant.RANDOMIZED)) {
            radioRandomized.setSelected(true);
        } else {
            radioCorrected.setSelected(true);
        }

        if(config.animalBehaviorVariant != null && config.animalBehaviorVariant.equals(AnimalBehaviorVariant.DEVIATION)) {
            radioDeviation.setSelected(true);
        } else {
            radioPredestination.setSelected(true);
        }
    }

    public Config loadSettingsFromFile(File file, String errorMessage, boolean testOnly) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Config config = new Gson().fromJson(br, Config.class);

            if(!testOnly) {
                labelErrorMessage.setText("");
                loadSettings(config);
            }
            return config;
        } catch(Exception err) {
            if(!testOnly) {
                labelErrorMessage.setText(errorMessage);
            }
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setOnCloseRequest(e -> primaryStage.close());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ConfigView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void handleStartSimulation(ActionEvent e) {
        try {
            Config config = new Config();
            int mapWidth = Integer.parseInt(inputMapWidth.getText());
            if(!(mapWidth > 0 && mapWidth <= 50)) {
                throw new Exception("0 < map width <= 50");
            }
            int mapHeight = Integer.parseInt(inputMapHeight.getText());
            if(!(mapHeight > 0 && mapHeight <= 50)) {
                throw new Exception("0 < map height <= 50");
            }
            int initialPlantQuantity = Integer.parseInt(inputPlantQuantity.getText());
            if(!(initialPlantQuantity > 0)) {
                throw new Exception("initial plant quantity > 0");
            }
            int initialAnimalQuantity = Integer.parseInt(inputAnimalQuantity.getText());
            if(!(initialAnimalQuantity > 0)) {
                throw new Exception("initial animal quantity > 0");
            }
            int initialAnimalEnergy = Integer.parseInt(inputAnimalEnergy.getText());
            if(!(initialAnimalEnergy > 0)) {
                throw new Exception("initial animal energy > 0");
            }
            int stuffedEnergy = Integer.parseInt(inputStuffedEnergy.getText());
            if(!(stuffedEnergy > 0)) {
                throw new Exception("stuffed energy > 0");
            }
            int multiplicationEnergy = Integer.parseInt(inputMultiplicationEnergy.getText());
            if(!(multiplicationEnergy > 0)) {
                throw new Exception("multiplication energy > 0");
            }
            int genomeLength = Integer.parseInt(inputGenomeLength.getText());
            if(!(genomeLength > 0)) {
                throw new Exception("genome length > 0");
            }
            int plantsPerDay = Integer.parseInt(inputPlantsPerDay.getText());
            if(!(plantsPerDay > 0)) {
                throw new Exception("plants per day> 0");
            }
            int plantEnergy = Integer.parseInt(inputPlantEnergy.getText());
            if(!(plantEnergy > 0)) {
                throw new Exception("plant energy > 0");
            }
            int minMutationQuantity = Integer.parseInt(inputMinMutationQuantity.getText());
            int maxMutationQuantity = Integer.parseInt(inputMaxMutationQuantity.getText());
            if(!(minMutationQuantity > 0)) {
                throw new Exception("min mutation quantity > 0");
            }
            if(!(maxMutationQuantity > 0)) {
                throw new Exception("max mutation quantity > 0");
            }
            if(!(minMutationQuantity <= maxMutationQuantity)) {
                throw new Exception("min mutation quantity <= max mutation quantity");
            }
            int refreshTime = Integer.parseInt(inputRefreshTime.getText());
            if(!(refreshTime >= 100)) {
                throw new Exception("refreshTime >= 100");
            }
            int shouldSaveDataToCSV = checkBoxSaveToCSV.isSelected() ? 1 : 0;

            config.mapWidth = mapWidth;
            config.mapHeight= mapHeight;
            config.initialPlantQuantity = initialPlantQuantity;
            config.initialAnimalQuantity = initialAnimalQuantity;
            config.initialAnimalEnergy = initialAnimalEnergy;
            config.stuffedEnergy = stuffedEnergy;
            config.multiplicationEnergy = multiplicationEnergy;
            config.genomeLength = genomeLength;
            config.plantsPerDay = plantsPerDay;
            config.plantEnergy = plantEnergy;
            config.minMutationQuantity = minMutationQuantity;
            config.maxMutationQuantity = maxMutationQuantity;
            config.refreshTime = refreshTime;
            config.shouldSaveDataToCSV = shouldSaveDataToCSV;

            config.boundaryVariant = radioEarth.isSelected() ? BoundaryVariant.EARTH : BoundaryVariant.HELLISH;
            config.mapVariant = radioClassicMap.isSelected() ? MapVariant.NORMAL : MapVariant.TOXIC;
            config.mutationVariant = radioRandomized.isSelected() ?  MutationVariant.RANDOMIZED : MutationVariant.CORRECTED;
            config.animalBehaviorVariant = radioDeviation.isSelected() ?  AnimalBehaviorVariant.DEVIATION : AnimalBehaviorVariant.PREDESTINATION;

            labelErrorMessage.setText("");
            openSimulationView(config);
        } catch (Exception error) {
            labelErrorMessage.setText("Invalid value! - " + error.getMessage().toLowerCase());
        }
    }

    public void handleLoadFromFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open configuration JSON file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json files", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        loadSettingsFromFile(file, "Make sure that selected file is valid", false);
    }

    public void openSimulationView(Config config) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SimulationView.fxml"));
            Parent root = loader.load();

            SimulationView controller = loader.getController();
            controller.initializeView(config, widowsOpened);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> controller.closeWindow());
            stage.setTitle("Simulation");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            widowsOpened += 1;
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}

