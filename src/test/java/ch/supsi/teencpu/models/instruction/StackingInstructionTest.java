package ch.supsi.teencpu.models.instruction;

import ch.supsi.teencpu.models.exceptions.FullAppStackExeption;
import ch.supsi.teencpu.models.exceptions.InvalidParametersException;
import ch.supsi.teencpu.models.exceptions.NoParametersException;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StackingInstructionTest {

    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkStackingInstruction() throws FullAppStackExeption {
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        StackingInstruction stackingInstruction = new StackingInstruction(4);
        stackingInstruction.execute(program);
        assertEquals(4, stack.pop());
        assertEquals(0, stack.size());
        StackingInstruction stackingInstruction2 = new StackingInstruction(7);
        StackingInstruction stackingInstruction3 = new StackingInstruction(8);
        stackingInstruction2.execute(program);
        stackingInstruction3.execute(program);
        assertEquals(8, stack.pop());
        assertEquals(1, stack.size());
        stackingInstruction3.execute(program);
    }
}