package ch.supsi.teencpu.javafx.jfxcontrollers;


import ch.supsi.teencpu.commands.Command;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends AppController implements Initializable {

    private Command goToSimulationCommand;
    private Command resetSimulationCommand;

    @FXML
    private VBox homeRoot;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button startNewSimBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void startNewSimulation() {
        try {
            goToSimulationCommand.execute();
            resetSimulationCommand.execute();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }


    public void setGoToSimulationCommand(Command goToSimulationCommand) {
        this.goToSimulationCommand = goToSimulationCommand;
    }

    public Command getResetSimulationCommand() {
        return resetSimulationCommand;
    }

    public void setResetSimulationCommand(Command resetSimulationCommand) {
        this.resetSimulationCommand = resetSimulationCommand;
    }

    @Override
    public Parent getRoot() {
        return homeRoot;
    }

    public Command getGoToSimulationCommand() {
        return goToSimulationCommand;
    }
}
