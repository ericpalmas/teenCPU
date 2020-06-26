package ch.supsi.teencpu.models.instruction;

import ch.supsi.teencpu.models.exceptions.*;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DuplicatingInstructionTest {

    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checException1(){
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        DuplicatingInstruction duplicatingInstruction = new DuplicatingInstruction();
        try{
            duplicatingInstruction.execute(program);
            fail();
        }catch(EmptyAppStackException | FullAppStackExeption ignored){ }
    }

    public void checException2(){
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);

        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        DuplicatingInstruction duplicatingInstruction = new DuplicatingInstruction();
        try{
            duplicatingInstruction.execute(program);
            fail();
        }catch(FullAppStackExeption | EmptyAppStackException ignored){ }
    }


    @Test
    public void checkDuplicatingExecution() throws EmptyAppStackException, FullAppStackExeption {
        stack.push(12);
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        DuplicatingInstruction duplicatingInstruction = new DuplicatingInstruction();
        duplicatingInstruction.execute(program);
        assertEquals(2, stack.size());
        assertEquals(1, pc.getValue().get());
    }
}