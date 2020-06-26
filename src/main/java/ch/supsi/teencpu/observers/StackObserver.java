package ch.supsi.teencpu.observers;

import ch.supsi.teencpu.models.stack.AppStack;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class StackObserver implements Observer {
    private VBox stackVBox;

    public VBox getStackVBox() {
        return stackVBox;
    }
    public StackObserver(VBox stackVBox) {
        this.stackVBox = stackVBox;
    }

    @Override
    public void update(Object appStack) {
        int vBoxStackSize = stackVBox.getChildren().size();
        for (int i = 1; i < stackVBox.getChildren().size(); i++) {
            ((Label) stackVBox.getChildren().get(i)).textProperty().bind(new SimpleStringProperty(" "));
        }
        for (int i = 0; i < ((AppStack) appStack).size(); i++) {
            ((Label) stackVBox.getChildren().get((vBoxStackSize - 1) - i)).textProperty().bind(((AppStack) appStack).getStack().get(i).asString());
        }
    }
}
