package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.FullAppStackExeption;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReadingInstructionTest {
    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkReadingInstructionExecution() throws FullAppStackExeption {
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        ReadingInstruction readingInstruction = new ReadingInstruction();
        readingInstruction.execute(program);
        assertEquals(1, program.getProgramCounter().getValue().get());
        assertEquals('C', program.getStack().pop());
        readingInstruction.execute(program);
        readingInstruction.execute(program);
        assertEquals(2, program.getStack().size());
        assertEquals(3, program.getProgramCounter().getValue().get());
        readingInstruction.execute(program);
        readingInstruction.execute(program);
        assertEquals(0, program.getStack().pop());
        readingInstruction.execute(program);
        assertEquals(0, program.getStack().pop());
    }
}