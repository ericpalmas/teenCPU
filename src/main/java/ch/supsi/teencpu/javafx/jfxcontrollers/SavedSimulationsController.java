package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.commands.Command;
import ch.supsi.teencpu.commands.LoadSimulationsCommand;
import ch.supsi.teencpu.commands.RemoveSimulationCommand;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.presentation.SimulationListCell;
import ch.supsi.teencpu.presentation.SimulationPresenter;
import ch.supsi.teencpu.repository.Simulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SavedSimulationsController extends AppController implements Initializable {

    private Command loadSimulationsCommand;
    private Command removeSimulationCommand;
    private Command openSSCommand;
    private ObservableList<Simulation> observSimulations;

    @FXML
    private HBox ssRoot;

    @FXML
    private ListView<Simulation> simulationsListView;

    @FXML
    private Label simulationInfoLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSimulationsCommand = new LoadSimulationsCommand(this);
        removeSimulationCommand = new RemoveSimulationCommand(this);

        simulationsListView.setCellFactory(simulationListView -> new SimulationListCell());
        observSimulations = FXCollections.observableArrayList();
        simulationsListView.setItems(observSimulations);

        loadAllsimulation();

        simulationsListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Simulation simulation = getSelectedSimulation();
            if (simulation != null) {
                SimulationPresenter presenter = new SimulationPresenter();
                simulationInfoLbl.setText(presenter.present(simulation));
            }
        });
    }

    @FXML
    public void openSelectedSimulation() {
        try {
            openSSCommand.execute();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public Simulation getSelectedSimulation() {
        return simulationsListView.getSelectionModel().getSelectedItem();
    }

    public void loadAllsimulation() {
        try {
            loadSimulationsCommand.execute();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Simulation> getObservSimulations() {
        return observSimulations;
    }

    @FXML
    void removeSelectedSimulation(ActionEvent event) {
        try {
            removeSimulationCommand.execute();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public void setOpenSSCommand(Command openSSCommand) {
        this.openSSCommand = openSSCommand;
    }

    @Override
    public Parent getRoot() {
        return ssRoot;
    }
}
