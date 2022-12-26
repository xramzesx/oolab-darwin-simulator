package oolab.darwin.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import oolab.darwin.Config;
import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;

public class ConfigView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //// TEMPORARY HARDCODED CONFIG ////

        //// TODO: Remove it and base only on config file

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

        //// LOAD DEFAULT DATA FROM CONFIG FILE ////

        //// TODO: make default loading from file

        //// SHOW CONFIG WINDOW ////

        //// TODO: make it multi-thread
        /// this line below is only for temporary development purpose
        this.run(config);

    }

    public void run( Config config ) {

        //// TODO: make it multi-thread
        SimulationView simulationView = new SimulationView(config);

    }
}
