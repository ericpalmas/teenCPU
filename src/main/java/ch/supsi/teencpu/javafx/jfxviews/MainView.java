package ch.supsi.teencpu.javafx.jfxviews;

import ch.supsi.teencpu.javafx.jfxcontrollers.AppController;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView<T extends AppController> extends TeencpuView<T> {

    public MainView(URL fxmlPath, ResourceBundle resourceBundle) {
        super(fxmlPath, resourceBundle);
    }

    @Override
    public Parent getTheView() {
        return fxViewRoot;
    }

    @Override
    public T getTheController() {
        return fxmlLoader.getController();
    }
}
