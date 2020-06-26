package ch.supsi.teencpu.models.program;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.instruction.DuplicatingInstruction;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.instruction.StackingInstruction;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import ch.supsi.teencpu.models.instruction.SumInstruction;
import ch.supsi.teencpu.models.undoRedo.Memento;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ProgramTest {
    @Test
    public void constructor0() {
        Program program = new Program();
        assertNotNull(program);
    }

    @Test
    public void constructor1() {
        Program program = new Program(new ArrayList<>(),Mockito.mock(AppStack.class),Mockito.mock(Input.class),Mockito.mock(Output.class),Mockito.mock(ProgramCounter.class));
        assertNotNull(program);
    }

    @Test
    public void checkInstructions() {
        ArrayList<Instruction> mockedInstructions = new ArrayList<>();
        Program program = new Program();

        mockedInstructions.add(Mockito.mock(StackingInstruction.class));
        mockedInstructions.add(Mockito.mock(StackingInstruction.class));
        mockedInstructions.add(Mockito.mock(SumInstruction.class));
        mockedInstructions.add(Mockito.mock(DuplicatingInstruction.class));

    }
    @Test
    public void checkInput(){
        Program program = new Program();
        Input mockedInput = Mockito.mock(Input.class);
        program.setInput(mockedInput);

        assertNotNull(program.getInput());
        assertEquals(mockedInput, program.getInput());
    }
    @Test
    public void checkOutput(){
        Program program = new Program();
        Output mockedOutput = Mockito.mock(Output.class);
        program.setOutput(mockedOutput);

        assertNotNull(program.getOutput());
        assertEquals(mockedOutput, program.getOutput());
    }
    @Test
    public void checkProgramCounter0(){
        Program program = new Program();
        ProgramCounter mockedProgramCounter = Mockito.mock(ProgramCounter.class);
        program.setProgramCounter(mockedProgramCounter);

        assertNotNull(program.getProgramCounter());
        assertEquals(mockedProgramCounter, program.getProgramCounter());
    }
    @Test
    public void checkProgramCounter1(){
        Program program = new Program();
        program.setProgramCounter(1);

        assertNotNull(program.getProgramCounter());
        assertEquals(1, program.getProgramCounter().getValue().get());
    }
    @Test
    public void checkAppStack(){
        Program program = new Program();
        AppStack mockedAppStack = Mockito.mock(AppStack.class);
        program.setStack(mockedAppStack);

        assertNotNull(program.getStack());
        assertEquals(mockedAppStack, program.getStack());
    }
    @Test
    public void checkSaveStatusOperations(){
        Program program = new Program(new ArrayList<>(),Mockito.mock(AppStack.class),
                Mockito.mock(Input.class), Mockito.mock(Output.class),Mockito.mock(ProgramCounter.class));

        assertNotNull(program.saveStateToMemento());
    }
    @Test
    public void checkGetStateFromMemento(){
        List<Instruction> instructions = new ArrayList<>();
        AppStack stack = new AppStack();
        Input input = new Input("ABBA");
        Output output = new Output();
        ProgramCounter pc = new ProgramCounter();

        instructions.add(new StackingInstruction(1));
        instructions.add(new StackingInstruction(1));
        instructions.add(new SumInstruction());
        instructions.add(new DuplicatingInstruction());

        Program program = new Program(instructions, stack, input, output, pc);
        Memento memento = new Memento(stack, pc, output, input);
        program.getStateFromMemento(memento);

        assertNotNull(program.getInstructions());
        assertNotNull(program.getStack());
        assertNotNull(program.getProgramCounter());
        assertNotNull(program.getOutput());
        assertNotNull(program.getInput());
    }
}