package oolab.darwin;

import oolab.darwin.enums.AnimalBehaviorVariant;
import oolab.darwin.enums.BoundaryVariant;
import oolab.darwin.enums.MapVariant;
import oolab.darwin.enums.MutationVariant;
import oolab.darwin.gui.ConfigView;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class ConfigViewTest {
    @Test
    void loadingConfigFromFile() {
        ConfigView view = new ConfigView();
        Config config = view.loadSettingsFromFile(new File("testSettings.json"), "Error", true);

        assertEquals(config.mapHeight, 30);
        assertEquals(config.mapWidth, 30);
        assertEquals(config.initialPlantQuantity, 10);
        assertEquals(config.initialAnimalQuantity, 5);
        assertEquals(config.initialAnimalEnergy, 10);
        assertEquals(config.stuffedEnergy, 5);
        assertEquals(config.multiplicationEnergy, 10);
        assertEquals(config.genomeLength, 5);
        assertEquals(config.boundaryVariant, BoundaryVariant.HELLISH);
        assertEquals(config.mapVariant, MapVariant.NORMAL);
        assertEquals(config.mutationVariant, MutationVariant.RANDOMIZED);
        assertEquals(config.animalBehaviorVariant, AnimalBehaviorVariant.DEVIATION);
        assertEquals(config.refreshTime, 200);
        assertEquals(config.plantsPerDay, 4);
        assertEquals(config.plantEnergy, 5);
        assertEquals(config.minMutationQuantity, 2);
        assertEquals(config.maxMutationQuantity, 6);
        assertEquals(config.shouldSaveDataToCSV, 1);

        Config config2 = view.loadSettingsFromFile(new File("testSettings2.json"), "Error", true);
        assertNull(config2.mapHeight);
        assertNull(config2.mapWidth);
        assertNull(config2.initialPlantQuantity);
        assertNull(config2.initialAnimalQuantity);
        assertNull(config2.initialAnimalEnergy);
        assertEquals(config2.stuffedEnergy, 5);
        assertEquals(config2.multiplicationEnergy, 10);
        assertNull(config2.genomeLength);
        assertNull(config2.boundaryVariant);
        assertNull(config2.mapVariant);
        assertNull(config2.mutationVariant);
        assertNull(config2.animalBehaviorVariant);
        assertNull(config2.refreshTime);
        assertNull(config2.plantsPerDay);
        assertNull(config2.plantEnergy);
        assertNull(config2.minMutationQuantity);
        assertNull(config2.maxMutationQuantity);
        assertNull(config2.shouldSaveDataToCSV);

    }
}
