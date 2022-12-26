package oolab.darwin.interfaces;

import javafx.scene.layout.*;

public interface IGuiElement extends IPositionObserver {
    VBox container = new VBox();

    void generate(IMapElement element);
    void update(IMapElement element);
}
