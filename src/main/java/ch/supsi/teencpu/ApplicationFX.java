package ch.supsi.teencpu;

import ch.supsi.teencpu.javafx.jfxcontrollers.MainController;
import ch.supsi.teencpu.javafx.jfxviews.MainView;
import ch.supsi.teencpu.javafx.jfxviews.TeencpuView;
import ch.supsi.teencpu.path.ApplicationPath;
import ch.supsi.teencpu.services.LanguageService;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class ApplicationFX extends Application {

    private TeencpuView<MainController> mainView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationPath.initApplicationPath();
        ApplicationPath.createSimulationFile();
        ApplicationPath.createLanguagePropertyFile();
        String currentLanguage = LanguageService.getInstance().getCurrentLanguage();

        Locale locale = new Locale(currentLanguage);

        Locale.setDefault(locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/strings");
        mainView = new MainView<>(getClass().getResource("/view/fxml/MainView.fxml"), resourceBundle);
        mainView.load();

        primaryStage.setTitle("Hello Teens");
        primaryStage.setScene(mainView.getScene());
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResource("/view/images/logo2.png").toString()));
        primaryStage.show();
    }

    public TeencpuView<MainController> getMainView() {
        return mainView;
    }
}
