package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.*;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
public class RemoveInstruction implements Instruction {
    @Override
    public boolean execute(Program program) throws EmptyAppStackException {
        if (program.getStack().size() > 0) {
            program.getStack().pop();
            program.getProgramCounter().increase();
        } else{
            throw new EmptyAppStackException("NESSUN ELEMENTO DA RIMUOVERE DALLO STACK", program.getProgramCounter().getValue().get() + 1);
        }
        return false;
    }
    @Override
    public Instruction getCopy(){
        return new RemoveInstruction();
    }
}