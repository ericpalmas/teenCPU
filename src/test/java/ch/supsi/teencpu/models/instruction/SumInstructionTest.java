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


public class SumInstructionTest {

    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkSumInstructionExecution() throws EmptyAppStackException {
        stack.push(4);
        stack.push(6);
        SumInstruction sumInstruction = new SumInstruction();
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        sumInstruction.execute(program);
        assertEquals(10, stack.pop());
        assertEquals(1, pc.getValue().get());
    }
}