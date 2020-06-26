package ch.supsi.teencpu.models.instruction;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.program.Program;
public interface Instruction {
    public boolean execute(Program program) throws ApplicationException;
    public Instruction getCopy();
}