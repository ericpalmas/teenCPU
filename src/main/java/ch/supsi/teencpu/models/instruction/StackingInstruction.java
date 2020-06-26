package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.FullAppStackExeption;
import ch.supsi.teencpu.models.program.Program;
public class StackingInstruction implements Instruction, Parameterizable {
    private int value;
    public StackingInstruction(int value) {
        this.value = value;
    }
    public StackingInstruction() {
        this.value = -1;
    }
    @Override
    public boolean execute(Program program) throws FullAppStackExeption {
        if(program.getStack().size() < 6){
            program.getStack().push(value);
            program.getProgramCounter().increase();
        }
        else{
            throw new FullAppStackExeption("LO STACK E' PIENO", program.getProgramCounter().getValue().get() + 1);
        }
        return false;
    }
    @Override
    public int getParameter() {
        return value;
    }
    @Override
    public void setParameter(int parameter) {
        this.value = parameter;
    }
    @Override
    public Instruction getCopy(){
        return new StackingInstruction();
    }
}