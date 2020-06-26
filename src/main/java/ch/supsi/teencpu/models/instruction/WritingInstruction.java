package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.exceptions.EmptyAppStackException;
import ch.supsi.teencpu.models.exceptions.FullOutputException;
import ch.supsi.teencpu.models.program.Program;
public class WritingInstruction implements Instruction {
    @Override
    public boolean execute(Program program) throws EmptyAppStackException, FullOutputException {
        if (program.getStack().size() > 0) {
            if(program.getOutput().getOutput().length()<8){
                int val = program.getStack().pop();
                program.getOutput().appendValue((char) val);
                program.getProgramCounter().increase();
            }else{
                throw new FullOutputException("OUTPUT TROPPO GRANDE", program.getProgramCounter().getValue().get() + 1);
            }
        } else{
            throw new EmptyAppStackException("NESSUN ELEMENTO DA LEGGERE NELLO STACK", program.getProgramCounter().getValue().get() + 1);
        }
        return false;
    }
    @Override
    public Instruction getCopy(){
        return new WritingInstruction();
    }
}