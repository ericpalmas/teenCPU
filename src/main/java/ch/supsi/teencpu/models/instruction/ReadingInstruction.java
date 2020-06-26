package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.FullAppStackExeption;
import ch.supsi.teencpu.models.program.Program;
public class ReadingInstruction implements Instruction {
    @Override
    public boolean execute(Program program) throws FullAppStackExeption {
        char value = program.getInput().getCurrentValue();
        if(value>0){
            if(program.getStack().size() < 6){
                program.getStack().push(value);
                program.getProgramCounter().increase();
            }
            else{
                throw new FullAppStackExeption("LO STACK E' PIENO", program.getProgramCounter().getValue().get() + 1);
            }
        }else{
            program.getStack().push(0);
            program.getProgramCounter().increase();
        }
        return false;
    }
    @Override
    public Instruction getCopy(){
        return new ReadingInstruction();
    }
}