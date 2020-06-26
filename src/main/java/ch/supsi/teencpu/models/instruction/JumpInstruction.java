package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.JumpExeption;
import ch.supsi.teencpu.models.program.Program;
public class JumpInstruction implements Instruction, Parameterizable {
    private int value;
    public JumpInstruction() {
        this.value = -1;
    }
    public JumpInstruction(int value) {
        this.value = value;
    }
    @Override
    public boolean execute(Program program) throws JumpExeption {
        if(value < 0 || value > program.getInstructions().size()){
            throw new JumpExeption("NESSUNA ISTRUZIONE CON PROGRAM COUNTER: " + value + " ");
        }else{
            program.getProgramCounter().set(value);
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
        return new JumpInstruction();
    }
}