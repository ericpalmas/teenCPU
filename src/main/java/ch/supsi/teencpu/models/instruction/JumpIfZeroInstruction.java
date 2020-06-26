package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.JumpExeption;
import ch.supsi.teencpu.models.program.Program;
public class JumpIfZeroInstruction implements Instruction, Parameterizable {
    private int value;
    public JumpIfZeroInstruction() {
        this.value = -1;
    }
    public JumpIfZeroInstruction(int value) {
        this.value = value;
    }
    @Override
    public boolean execute(Program program) throws JumpExeption {
        int stackValue = program.getStack().pop();
        if (stackValue == 0) {
            if( value < 0 || value > program.getInstructions().size()){
                throw new JumpExeption("NESSUNA ISTRUZIONE CON PROGRAM COUNTER: " + value + " ");
            }else{
                program.getProgramCounter().set(value);
            }
        } else {
            program.getProgramCounter().increase();
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
        return new JumpIfZeroInstruction(value);
    }
}