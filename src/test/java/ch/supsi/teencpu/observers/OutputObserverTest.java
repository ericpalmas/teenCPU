package ch.supsi.teencpu.observers;

import ch.supsi.teencpu.models.output.Output;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OutputObserverTest {

    @Test
    public void constructor0() {
        OutputObserver outputObserver = new OutputObserver(Mockito.mock(HBox.class));
        assertNotNull(outputObserver);
        assertNotNull(outputObserver.getOutputHBox());
    }

    @Ignore
    @Test
    public void checkUpdate() {
        Output output = new Output();
        output.appendValue('C');
        output.appendValue('i');
        output.appendValue('a');
        output.appendValue('o');

        HBox hBox = new HBox();
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(new Label(" "));

        OutputObserver outputObserver = new OutputObserver(hBox);
        outputObserver.update(output);

        for(int i=0; i<outputObserver.getOutputHBox().getChildren().size();i++){
            System.out.println(outputObserver.getOutputHBox().getChildren().get(i));
        }

    }
}
