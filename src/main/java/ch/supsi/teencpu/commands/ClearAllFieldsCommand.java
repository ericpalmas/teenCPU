package ch.supsi.teencpu.commands;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.CodeArea;


public class ClearAllFieldsCommand implements Command {

    private TextField inputTextField;
    private Label inputIntegersLbl;
    private CodeArea codeArea;
    private Label pcValueLbl;
    private VBox stackVBox;
    private HBox outputHBox;


    public ClearAllFieldsCommand(TextField inputTextField, Label inputIntegersLbl, CodeArea codeArea, Label pcValueLbl, VBox stackVBox, HBox outputHBox) {
        this.inputTextField = inputTextField;
        this.inputIntegersLbl = inputIntegersLbl;
        this.codeArea = codeArea;
        this.pcValueLbl = pcValueLbl;
        this.stackVBox = stackVBox;
        this.outputHBox = outputHBox;
    }

    @Override
    public void execute() {
        inputTextField.setText("");
        inputIntegersLbl.setText("");
        codeArea.replaceText("");
        pcValueLbl.textProperty().unbind();
        pcValueLbl.setText("0");
        for (Node node : stackVBox.getChildren()) {
            Label label = (Label) node;
            label.textProperty().unbind();
            label.setText("");
        }
        for (Node node : outputHBox.getChildren()) {
            Label label = (Label) node;
            label.textProperty().unbind();
            label.setText("");
        }
    }
}
