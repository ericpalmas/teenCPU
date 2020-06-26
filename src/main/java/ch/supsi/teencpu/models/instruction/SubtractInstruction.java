package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.EmptyAppStackException;
import ch.supsi.teencpu.models.program.Program;
public class SubtractInstruction implements Instruction {
    @Override
    public boolean execute(Program program) throws EmptyAppStackException {
        if (program.getStack().size() > 1) {
            int val1 = program.getStack().pop();
            int val2 = program.getStack().pop();
            program.getStack().push(val1 - val2);
            program.getProgramCounter().increase();
        } else{
            throw new EmptyAppStackException("TROPPI POCHI ELEMENTI NELLO STACK",program.getProgramCounter().getValue().get() + 1);
        }
        return false;
    }
    @Override
    public Instruction getCopy(){
        return new SubtractInstruction();
    }
}