package ch.supsi.teencpu.models.instruction;

import ch.supsi.teencpu.models.exceptions.EmptyAppStackException;
import ch.supsi.teencpu.models.exceptions.InvalidParametersException;
import ch.supsi.teencpu.models.exceptions.NoParametersException;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class RemoveInstructionTest {
    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkRemoveInstructionExecution() throws EmptyAppStackException {
        stack.push(12);
        stack.push(11);
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        RemoveInstruction removeInstruction = new RemoveInstruction();
        removeInstruction.execute(program);
        assertEquals(1, pc.getValue().get());
        assertEquals(1, stack.size());
        removeInstruction.execute(program);
        assertEquals(2, pc.getValue().get());
        assertEquals(0, stack.size());
    }
}