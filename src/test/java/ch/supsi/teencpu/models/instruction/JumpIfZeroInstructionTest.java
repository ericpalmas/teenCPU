package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.JumpExeption;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class JumpIfZeroInstructionTest {
    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkIfZeroInstructionExeption() {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        stack.push(4);
        stack.push(0);
        JumpIfZeroInstruction jumpIfZeroInstruction = new JumpIfZeroInstruction(4);
        try{
            jumpIfZeroInstruction.execute(program);
            fail();
        }catch(JumpExeption e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkExecution1() throws JumpExeption {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        stack.push(4);
        stack.push(0);
        Program program = new Program(instructions,stack,input,output,pc );
        JumpIfZeroInstruction jumpIfZeroInstruction = new JumpIfZeroInstruction(1);
        jumpIfZeroInstruction.execute(program);
        assertEquals(1,program.getProgramCounter().getValue().get());
    }


    @Test
    public void checkExecution2() throws JumpExeption {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        stack.push(4);
        stack.push(7);
        JumpIfZeroInstruction jumpIfZeroInstruction = new JumpIfZeroInstruction(0);
        jumpIfZeroInstruction.execute(program);
        assertEquals(1,program.getProgramCounter().getValue().get());
    }

}