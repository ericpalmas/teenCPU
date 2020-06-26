package ch.supsi.teencpu.observers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

public class StackObserverTest {

    @Test
    public void constructor0() {
        StackObserver stackObserver = new StackObserver(Mockito.mock(VBox.class));
        assertNotNull(stackObserver);
        assertNotNull(stackObserver.getStackVBox());
    }

    @Test
    @Ignore
    public void update() {
    }
}