package oolab.darwin.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oolab.darwin.Config;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

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
    public void initialize() {



        Config config = new Config();
        inputMapHeight.setText(config.mapHeight.toString());
        inputMapWidth.setText(config.mapWidth.toString());
        inputPlantQuantity.setText(config.initialPlantQuantity.toString());
        inputAnimalQuantity.setText(config.initialAnimalQuantity.toString());
        inputAnimalEnergy.setText(config.initialAnimalEnergy.toString());
        inputStuffedEnergy.setText(config.stuffedEnergy.toString());
        inputMultiplicationEnergy.setText(config.multiplicationEnergy.toString());
        inputGenomeLength.setText(config.genomeLength.toString());
        inputMultiplicationEnergy.setText(config.multiplicationEnergy.toString());
        inputRefreshTime.setText(config.refreshTime.toString());

        if(config.mapVariant.equals(MapVariant.NORMAL)) {
            radioClassicMap.setSelected(true);
        } else {
            radioToxicMap.setSelected(true);
        }

        if(config.boundaryVariant.equals(BoundaryVariant.EARTH)) {
            radioEarth.setSelected(true);
        } else {
            radioHellish.setSelected(true);
        }

        labelErrorMessage.setText("");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ConfigView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception error) {
            error.printStackTrace();
        }


        //// TODO: make it multi-thread
        /// this line below is only for temporary development purpose

    }

    public void run( Config config ) {

        //// TODO: make it multi-thread
        SimulationView simulationView = new SimulationView(config);

    }


    public void handleClick(ActionEvent e) {
        try {
            Config config = new Config();

            config.mapWidth = Integer.parseInt(inputMapWidth.getText());
            config.mapHeight= Integer.parseInt(inputMapHeight.getText());

            config.initialPlantQuantity = Integer.parseInt(inputPlantQuantity.getText());
            config.initialAnimalQuantity = Integer.parseInt(inputAnimalQuantity.getText());
            config.initialAnimalEnergy = Integer.parseInt(inputAnimalEnergy.getText());

            config.stuffedEnergy = Integer.parseInt(inputStuffedEnergy.getText());
            config.multiplicationEnergy = Integer.parseInt(inputMultiplicationEnergy.getText());
            config.genomeLength = Integer.parseInt(inputGenomeLength.getText());

            config.boundaryVariant = radioEarth.isSelected() ? BoundaryVariant.EARTH : BoundaryVariant.HELLISH;
            config.mapVariant = radioClassicMap.isSelected() ? MapVariant.NORMAL : MapVariant.TOXIC;
            config.mutationVariant = MutationVariant.RANDOMIZED;
            config.animalBehaviorVariant = AnimalBehaviorVariant.DEVIATION;

            config.refreshTime = Integer.parseInt(inputRefreshTime.getText());
            labelErrorMessage.setText("");
            this.run(config);
        } catch (Exception error) {
            labelErrorMessage.setText("Invalid value " + error.getMessage().toLowerCase());
        }
    }
}

