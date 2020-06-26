package ch.supsi.teencpu.javafx.jfxviews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class TeencpuView<T> {

    protected Parent fxViewRoot;
    protected FXMLLoader fxmlLoader;
    protected Scene scene;
    private URL fxmlPath;
    private ResourceBundle resourceBundle;

    public TeencpuView(URL fxmlPath, ResourceBundle resourceBundle) {
        this.fxmlPath = fxmlPath;
        this.resourceBundle = resourceBundle;

    }

    public void load() throws IOException {
        fxmlLoader = new FXMLLoader(fxmlPath, resourceBundle);
        fxViewRoot = fxmlLoader.load();
        scene = new Scene(fxViewRoot);
    }


    public abstract Parent getTheView();

    public abstract T getTheController();

    public void show(){
        Stage stage = new Stage();
        stage.setScene(getScene());
        stage.show();
    }

    public void close(){
        Stage stage =(Stage) fxViewRoot.getScene().getWindow();
        stage.close();
    }

    public Scene getScene() {
        return scene;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
