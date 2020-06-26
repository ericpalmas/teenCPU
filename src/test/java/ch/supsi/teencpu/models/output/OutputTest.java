package ch.supsi.teencpu.models.output;

import ch.supsi.teencpu.observers.Observer;
import javafx.beans.binding.ObjectBinding;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OutputTest {

    private Output output = new Output();

    @Test
    public void checkAppendValue() {
        output.appendValue('c');
        output.appendValue('i');
        output.appendValue('a');
        output.appendValue('o');
        assertEquals("ciao", output.getOutput());
    }

    @Test
    public void checkRegister(){
        Observer mockedObserver = Mockito.mock(Observer.class);
        output.register(mockedObserver);
        assertNotNull(output.getObservers());
    }

    @Test
    public void checkUnregister(){
        Observer mockedObserver = Mockito.mock(Observer.class);
        output.register(mockedObserver);
        output.unregister(mockedObserver);

        assertEquals(0,output.getObservers().size());
    }

    @Test
    public void checkDeepCopy(){
        output.appendValue('c');
        output.appendValue('i');
        output.appendValue('a');
        output.appendValue('o');
        Output outputCopied = output.deepCopy();

        assertEquals(output.getOutput(),outputCopied.getOutput());
    }

}