package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.program.Program;
public class TerminateInstruction implements Instruction{
    @Override
    public boolean execute(Program program) {
        return true;
    }
    @Override
    public Instruction getCopy() {
        return new TerminateInstruction();
    }
}