package ch.supsi.teencpu.javafx.jfxcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationController extends AppController implements Initializable {

    @FXML
    private VBox informationRoot;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane instructionsInfoPane;

    @FXML
    private TitledPane aboutPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accordion.setExpandedPane(accordion.getPanes().get(0));

    }


    @Override
    public Parent getRoot() {
        return informationRoot;
    }
}
