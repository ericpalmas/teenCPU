package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.EmptyAppStackException;
import ch.supsi.teencpu.models.exceptions.FullAppStackExeption;
import ch.supsi.teencpu.models.program.Program;
public class DuplicatingInstruction implements Instruction {
    @Override
    public boolean execute(Program program) throws EmptyAppStackException, FullAppStackExeption {
        if (program.getStack().size() > 0) {
            if(program.getStack().size() < 6){
                int val = program.getStack().pop();
                program.getStack().push(val);
                program.getStack().push(val);
                program.getProgramCounter().increase();
            }else  {
                throw new FullAppStackExeption("LO STACK E' PIENO", program.getProgramCounter().getValue().get() + 1);
            }
        }else{
            throw new EmptyAppStackException("NESSUN ELEMENTO DA RIMUOVERE DALLO STACK", program.getProgramCounter().getValue().get() + 1);
        }
        return false;
    }
    @Override
    public Instruction getCopy(){
        return new DuplicatingInstruction();
    }
}