package ch.supsi.teencpu.models.stack;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.observers.Observer;
import ch.supsi.teencpu.observers.Subject;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AppStackTest {
    @Test
    public void constructor0() {
        AppStack stack = new AppStack();
        assertNotNull(stack);
    }

    @Test
    public void checkPushAndPop() {
        AppStack stack = new AppStack();
        stack.push('c');
        stack.push(120);
        assertEquals(120, stack.pop());
        assertEquals('c', stack.pop());
    }

    @Test
    public void checkStackSize() {
        AppStack stack = new AppStack();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    public void checkRegister(){
        AppStack stack = new AppStack();
        Observer mockedObserver = Mockito.mock(Observer.class);
        stack.register(mockedObserver);
        assertNotNull(stack.getObservers());
    }

    @Test
    public void checkUnregister(){
        AppStack stack = new AppStack();
        Observer mockedObserver = Mockito.mock(Observer.class);
        stack.register(mockedObserver);
        stack.unregister(mockedObserver);

        assertEquals(0,stack.getObservers().size());
    }

    @Test
    public void checkDeepCopy(){
        AppStack stack = new AppStack();
        stack.push(100);
        stack.push(120);
        AppStack stackCopied = stack.deepCopy();

        assertEquals(stack.getStack(),stackCopied.getStack());
    }
}