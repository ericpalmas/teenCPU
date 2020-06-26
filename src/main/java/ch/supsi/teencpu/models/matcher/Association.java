package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.exceptions.CompilerException;
import ch.supsi.teencpu.models.instruction.Instruction;

public class Association {

    private Instruction instruction;
    private String matcher;

    private Association(Instruction instruction, String matcher) {
        this.instruction = instruction;
        this.matcher = matcher;

    }

    public static Association createAssociation(Instruction instruction, String matcher){
        return new Association(instruction, matcher);
    }

    public Instruction getInstruction() {
        return instruction.getCopy();
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String toString() {
        return matcher;
    }
}
