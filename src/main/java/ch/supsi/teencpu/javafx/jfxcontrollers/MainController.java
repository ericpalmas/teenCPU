package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.commands.*;
import ch.supsi.teencpu.javafx.jfxviews.*;
import ch.supsi.teencpu.repository.JsonRepository;
import ch.supsi.teencpu.services.CompilerService;
import ch.supsi.teencpu.services.ExecutorService;
import ch.supsi.teencpu.services.LanguageService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends AppController implements Initializable {

    private TeencpuView<HomeController> homeView;
    private TeencpuView<SimulationController> simulationView;
    private TeencpuView<SettingController> settingView;
    private TeencpuView<InformationController> informationView;
    private TeencpuView<SavedSimulationsController> ssView;


    @FXML
    private BorderPane mainRoot;

    @FXML
    private HBox topMenuHBox;

    @FXML
    private Button homeBtn;

    @FXML
    private Button simulationBtn;

    @FXML
    private Button settingBtn;

    @FXML
    private Button infoBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homeView = new HomeView<>(getClass().getResource("/view/fxml/HomeView.fxml"), resourceBundle);
        simulationView = new SimulationView<>(getClass().getResource("/view/fxml/SimulationView.fxml"), resourceBundle);
        settingView = new SettingView<>(getClass().getResource("/view/fxml/SettingView.fxml"), resourceBundle);
        informationView = new InformationView<>(getClass().getResource("/view/fxml/InformationView.fxml"), resourceBundle);
        ssView = new SavedSimulationsView<>(getClass().getResource("/view/fxml/SavedSimulationsView.fxml"), resourceBundle);

        try {
            homeView.load();
            simulationView.load();
            settingView.load();
            informationView.load();
            ssView.load();


            simulationView.getTheController().setSsView(ssView);

            // Set commands
            Command initNewSimCommand = new InitNewSimulationCommand(ExecutorService.getInstance(), simulationView.getTheController(), CompilerService.getInstance(), resourceBundle);
            simulationView.getTheController()
                    .setInitNewSimCommand(initNewSimCommand)
                    .setExecutionMacroCommand(new ExecutionMacroCommand())
                    .setSaveSimulationCommand(new SaveSimulationCommand(JsonRepository.getInstance(), simulationView.getTheController()))
                    .setOpenInstrFileCommand(new OpenFileCommand(simulationView.getTheController()));
            settingView.getTheController().setChangeLanguageCommand(new ChangeLanguageCommand(LanguageService.getInstance()));
            homeView.getTheController().setGoToSimulationCommand(new GoToSimulationCommand(this));
            ssView.getTheController().setOpenSSCommand(new OpenSSCommand(simulationView.getTheController()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainRoot.setCenter(homeView.getTheView());
        styleClickedButton(homeBtn);
    }

    @FXML
    public void goHome() {
        mainRoot.setCenter(homeView.getTheView());
        styleClickedButton(homeBtn);
    }

    @FXML
    public void goToInformation() {
        mainRoot.setCenter(informationView.getTheView());
        styleClickedButton(infoBtn);
    }

    @FXML
    public void goToSetting() {
        mainRoot.setCenter(settingView.getTheView());
        styleClickedButton(settingBtn);
    }

    @FXML
    public void goToSimulation() {
        mainRoot.setCenter(simulationView.getTheView());
        styleClickedButton(simulationBtn);
    }

    public void setView(Parent rootPanel) {
        mainRoot.setCenter(rootPanel);
    }

    private void styleClickedButton(Button clickedBtn) {
        for (int i = 0; i < topMenuHBox.getChildren().size(); i++) {
            Node node = topMenuHBox.getChildren().get(i);
            if (node instanceof Button) {
                node.getStyleClass().setAll("menu-buttons");
            }
        }
        clickedBtn.getStyleClass().setAll("menu-buttons-clicked");
    }

    @Override
    public Parent getRoot() {
        return mainRoot;
    }

    public TeencpuView<HomeController> getHomeView() {
        return homeView;
    }

    public TeencpuView<SimulationController> getSimulationView() {
        return simulationView;
    }

    public TeencpuView<SettingController> getSettingView() {
        return settingView;
    }

    public TeencpuView<InformationController> getInformationView() {
        return informationView;
    }

    public TeencpuView<SavedSimulationsController> getSsView() {
        return ssView;
    }
}
