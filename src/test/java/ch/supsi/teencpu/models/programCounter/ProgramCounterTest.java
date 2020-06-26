package ch.supsi.teencpu.models.programCounter;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProgramCounterTest {
    @Test
    public void constructor0() {
        ProgramCounter pc = new ProgramCounter();
        assertNotNull(pc);
        assertEquals(0,pc.getValue().get());
    }

    @Test
    public void constructor1() {
        ProgramCounter pc = new ProgramCounter(1);
        assertNotNull(pc);
        assertEquals(1,pc.getValue().get());
    }

    @Test
    public void checkDeepCopy(){
        ProgramCounter pc = new ProgramCounter();
        pc.set(4);
        ProgramCounter mockedPc = pc.deepCopy();
        assertEquals(pc.getValue().get(),mockedPc.getValue().get());
    }

    @Test
    public void checkProgramCounterIncrease() {
        ProgramCounter pc = new ProgramCounter();
        pc.increase();
        pc.increase();
        assertEquals(2, pc.getValue().get());
        assertEquals(3, new ProgramCounter(3).getValue().get());
    }

    @Test
    public void checkProgramCounterReset() {
        ProgramCounter pc = new ProgramCounter();
        pc.increase();
        pc.increase();
        pc.reset();
        assertEquals(0, pc.getValue().get());
    }

    @Test
    public void checkProgramCounterSet() {
        ProgramCounter pc = new ProgramCounter();
        pc.set(12);
        assertEquals(12, pc.getValue().get());
    }
}