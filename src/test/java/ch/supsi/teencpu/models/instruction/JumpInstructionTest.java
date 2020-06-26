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
import static org.junit.Assert.fail;

public class JumpInstructionTest {
    private AppStack stack = new AppStack();
    private Input input = new Input("Ciao");
    private Output output = new Output();
    private ProgramCounter pc = new ProgramCounter();

    @Test
    public void checkJumpInstructionExecution() {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new ReadingInstruction());
        instructions.add(new ReadingInstruction());
        instructions.add(new WritingInstruction());
        instructions.add(new WritingInstruction());
        instructions.add(new WritingInstruction());
        Program program = new Program();
        program.setStack(stack);
        program.setInput(input);
        program.setOutput(output);
        program.setProgramCounter(pc);
        JumpInstruction jumpInstruction = new JumpInstruction(2);
        try{
            jumpInstruction.execute(program);
            fail();
        }catch(JumpExeption e){
            e.printStackTrace();
        }
    }
}