package ch.supsi.teencpu.observers;

import ch.supsi.teencpu.models.output.Output;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class OutputObserver implements Observer {
    private HBox outputHBox;

    public HBox getOutputHBox() { return outputHBox; }

    public OutputObserver(HBox inputHBox1) {
        this.outputHBox = inputHBox1;
    }

    @Override
    public void update(Object out) {
        Output output = (Output) out;
        for (int i = 0; i < outputHBox.getChildren().size(); i++) {
            ((Label) outputHBox.getChildren().get(i)).textProperty().bind(new SimpleStringProperty(" "));
        }
        for (int i = 0, n = output.getOutput().length(); i < n; i++) {
            ((Label) outputHBox.getChildren().get(i)).textProperty().bind(new SimpleStringProperty(" " + output.getOutput().charAt(i)));
        }
    }
}
