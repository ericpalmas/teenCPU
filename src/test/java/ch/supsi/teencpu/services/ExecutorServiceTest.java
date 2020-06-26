package ch.supsi.teencpu.services;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.instruction.DuplicatingInstruction;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.instruction.StackingInstruction;
import ch.supsi.teencpu.models.instruction.SumInstruction;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import ch.supsi.teencpu.observers.OutputObserver;
import ch.supsi.teencpu.observers.StackObserver;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ExecutorServiceTest {

    @Test
    public void constructor0() {
        ExecutorService model = ExecutorService.getInstance();
        assertNotNull(model);
    }

    @Test
    public void instance0() {
        ExecutorService model1 = ExecutorService.getInstance();
        assertNotNull(model1);

        ExecutorService model2 = ExecutorService.getInstance();
        assertNotNull(model2);

        assertEquals(model1, model2);
    }

    @Ignore
    @Test
    public void checkExecutionTest() {
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
        ExecutorService executorService = ExecutorService.getInstance();

        HBox hBox = new HBox();
        OutputObserver outputObserver = new OutputObserver(hBox);

        VBox vBox = new VBox();
        StackObserver stackObserver = new StackObserver(vBox);

        //executorService.execute(program,2, outputObserver, stackObserver);
        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
    }
}