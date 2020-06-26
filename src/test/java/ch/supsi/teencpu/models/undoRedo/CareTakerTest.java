package ch.supsi.teencpu.models.undoRedo;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CareTakerTest {
    private List<Instruction> instructions = new ArrayList<>();
    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkStates() {
        CareTaker careTaker = new CareTaker();
        Program originator = new Program(instructions, stack, input, output, pc);
        originator.setProgramCounter(new ProgramCounter(8));
        originator.setInput(new Input("Ciao"));
        stack.push(1);
        originator.setStack(stack);
        careTaker.add(originator.saveStateToMemento());

        originator.setProgramCounter(new ProgramCounter(5));
        originator.setInput(new Input("Mondo"));
        stack.push(2);
        stack.push(3);
        originator.setStack(stack);
        careTaker.add(originator.saveStateToMemento());

        originator.setProgramCounter(new ProgramCounter(1));
        originator.setInput(new Input("Fine"));
        stack.push(4);
        stack.push(5);
        careTaker.add(originator.saveStateToMemento());

        System.out.println(careTaker);


        originator.getStateFromMemento(careTaker.getCurrentState());
        assertEquals("Fine",originator.getInput().getValue());
        assertEquals(5,originator.getStack().size());
        assertEquals(1,originator.getProgramCounter().getValue().get());

        originator.getStateFromMemento(careTaker.getPreviousState());
        assertEquals("Mondo",originator.getInput().getValue());
        assertEquals(3,originator.getStack().size());
        assertEquals(5,originator.getProgramCounter().getValue().get());

        originator.getStateFromMemento(careTaker.getPreviousState());
        assertEquals("Ciao",originator.getInput().getValue());
        assertEquals(1,originator.getStack().size());
        assertEquals(8,originator.getProgramCounter().getValue().get());

        originator.getStateFromMemento(careTaker.getNextState());
        assertEquals("Mondo",originator.getInput().getValue());
        assertEquals(3,originator.getStack().size());
        assertEquals(5,originator.getProgramCounter().getValue().get());
    }
}