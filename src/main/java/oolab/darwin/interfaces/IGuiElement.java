package oolab.darwin.interfaces;

import javafx.scene.layout.*;

public interface IGuiElement <T> extends IObserver<T> {
    VBox container = new VBox();

    void generate(IMapElement element);
    void update(IMapElement element);
}
