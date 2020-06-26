package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.commands.*;
import ch.supsi.teencpu.javafx.jfxviews.TeencpuView;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.presentation.CodeEditor;
import ch.supsi.teencpu.presentation.InputIntegerPresenter;
import ch.supsi.teencpu.repository.Simulation;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.net.URL;
import java.util.ResourceBundle;


public class SimulationController extends AppController implements Initializable {


    private TeencpuView<SavedSimulationsController> ssView;

    private Command initNewSimCommand;
    private MacroCommand executionMacroCommand;
    private Command saveSimulationCommand;
    private Command openInstrFileCommand;

    private boolean newSimulation = false;

    @FXML
    private HBox simulationRoot;

    @FXML
    private TextField inputTextField;

    @FXML
    private StackPane codeAreaStackPane;

    @FXML
    private CodeArea codeArea;

    @FXML
    private Label inputIntegersLbl;

    @FXML
    private VBox stackVBox;

    @FXML
    private Label stackLbl;

    @FXML
    private ImageView playPauseImageView;

    @FXML
    private Label pcValueLbl;

    @FXML
    private HBox outputHBox;

    @FXML
    public Button seeLastSimBtn;

    @FXML
    private Button stepOverBtn;

    @FXML
    private Button executeSimulationBtn;

    @FXML
    private Button stepBackBtn;


    @FXML
    private Button msgBtn;


    @FXML
    private Button buildBtn;


    @FXML
    private Label notificationLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CodeEditor codeEditor = new CodeEditor();
        codeEditor.initCodeEditor(codeAreaStackPane, codeArea);

        inputTextField.setOnKeyTyped(event -> {
            updateInputIntegersLabel();
        });

        BooleanBinding inputValid = Bindings.createBooleanBinding(() -> !inputTextField.getText().isBlank(), inputTextField.textProperty());
        BooleanBinding instructionsValid = Bindings.createBooleanBinding(() -> !codeArea.getText().isBlank()
                        && codeArea.getText().contains(resourceBundle.getString("terminate"))
                , codeArea.textProperty());

        buildBtn.disableProperty().bind(inputValid.not().or(instructionsValid.not()));
        executeSimulationBtn.disableProperty().bind(buildBtn.disableProperty());
        stepOverBtn.disableProperty().bind(buildBtn.disableProperty());
        stepBackBtn.disableProperty().bind(buildBtn.disableProperty());
    }

    @FXML
    void buildSimulation() {
        try {
            initNewSimCommand.execute();
            new NotifySuccessCommand(notificationLbl, "Build success").execute();
        } catch (ApplicationException e) {
            new NotifyFailureCommand(notificationLbl, "Build Failed").execute();
        }
    }

    @FXML
    public void executeSimulation() {
        try {
            executionMacroCommand.execute();
        } catch (ApplicationException e) {
            new NotifyFailureCommand(notificationLbl, e.getMessage()).execute();
        }
    }

    @FXML
    public void stepOver() {
        try {
            executionMacroCommand.executeNext();
        } catch (ApplicationException e) {
            new NotifyFailureCommand(notificationLbl, e.getMessage()).execute();
        }
    }

    @FXML
    public void stepBack() {
        try {
            executionMacroCommand.executePrevious();
        } catch (ApplicationException e) {
            new NotifyFailureCommand(notificationLbl, e.getMessage()).execute();
        }
    }


    @FXML
    public void openInstructionsFile() {
        try {
            openInstrFileCommand.execute();
        } catch (Exception e) {
            new NotifyFailureCommand(notificationLbl, e.getMessage()).execute();
        }

    }

    @FXML
    public void saveSimulation() {
        try {
            saveSimulationCommand.execute();
            new NotifySuccessCommand(notificationLbl, "Saved!").execute();
        } catch (ApplicationException e) {
            new NotifyFailureCommand(notificationLbl, e.getMessage()).execute();
        }
    }

    @FXML
    public void openSavedSimulations() {
        ssView.show();
        ((Stage) ssView.getScene().getWindow()).setTitle(ssView.getResourceBundle().getString("save.simulation.title"));
    }

    @FXML
    void clearFields() {
        new ClearAllFieldsCommand(inputTextField, inputIntegersLbl
                , codeArea, pcValueLbl, stackVBox, outputHBox).execute();
    }

    public void openSelectedSimulation(Simulation simulation) {
        codeArea.replaceText(simulation.getInstructions().stream().map(s -> s.toUpperCase() + ";\n").reduce((s, s1) -> s + s1).get());
        inputTextField.setText(simulation.getInput().getValue());
        updateInputIntegersLabel();
        ssView.close();
    }

    private void updateInputIntegersLabel() {
        InputIntegerPresenter presenter = new InputIntegerPresenter();
        String inputText = inputTextField.getText();
        inputIntegersLbl.setText(presenter.present(inputText));
    }


    public SimulationController setInitNewSimCommand(Command initNewSimCommand) {
        this.initNewSimCommand = initNewSimCommand;
        return this;
    }

    public SimulationController setSaveSimulationCommand(Command saveSimulationCommand) {
        this.saveSimulationCommand = saveSimulationCommand;
        return this;
    }

    public SimulationController setOpenInstrFileCommand(Command openInstrFileCommand) {
        this.openInstrFileCommand = openInstrFileCommand;
        return this;
    }

    public SimulationController setExecutionMacroCommand(MacroCommand executionMacroCommand) {
        this.executionMacroCommand = executionMacroCommand;
        return this;
    }

    public TeencpuView<SavedSimulationsController> getSavedSimulationView() {
        return ssView;
    }

    public Command getOpenInstrFileCommand() {
        return openInstrFileCommand;
    }

    public Label getPcValueLbl() {
        return pcValueLbl;
    }

    @Override
    public Parent getRoot() {
        return simulationRoot;
    }

    public TextField getInputTextField() {
        return inputTextField;
    }

    public CodeArea getCodeArea() {
        return codeArea;
    }

    public HBox getOutputHBox() {
        return outputHBox;
    }

    public VBox getStackVBox() {
        return stackVBox;
    }

    public StackPane getCodeAreaStackPane() {
        return codeAreaStackPane;
    }

    public Button getMsgBtn() {
        return msgBtn;
    }

    public void setSsView(TeencpuView<SavedSimulationsController> ssView) {
        this.ssView = ssView;
    }

    public Button getBuildBtn() {
        return buildBtn;
    }

    public Button getExecuteSimulationBtn() {
        return executeSimulationBtn;
    }

    public Label getNotificationLbl() {
        return notificationLbl;
    }

}
