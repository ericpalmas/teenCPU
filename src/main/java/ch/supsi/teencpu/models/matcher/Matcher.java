package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.exceptions.InvalidParametersException;
import ch.supsi.teencpu.models.exceptions.WrongSyntaxException;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.instruction.Parameterizable;

import java.util.ArrayList;
import java.util.List;

public class Matcher {

    private Lines lines;
    private List<Association> matchers;

    private Matcher(Lines lines, List<Association> matchers) {
        this.lines = lines;
        this.matchers = matchers;
    }

    public Matcher(Lines lines) {
        if (lines != null) {
            this.lines = lines;
            this.matchers = new ArrayList<>();
        }
        throw (new NullPointerException());
    }

    public static Matcher createMatcher(Lines lines, List<Association> matchers) {
        if (lines != null && matchers != null) {
            return new Matcher(lines, matchers);
        } else
            throw (new NullPointerException());
    }

    public Lines getLines() {
        return lines;
    }

    public boolean addMatcher(Association a) {
        if (a != null)
            return matchers.add(a);
        else
            throw new NullPointerException();
    }

    public List<Instruction> getInstructions() throws WrongSyntaxException {
        List<Instruction> inputInstructions = new ArrayList<>();
        for (Line l : lines.getInputLines()) {
            inputInstructions.add(match(l));
        }
        return inputInstructions;
    }

    private Instruction match(Line input) throws WrongSyntaxException {
        Instruction i = null;
        for (Association a : matchers) {
            if (input.getValue().matches(a.getMatcher()))
                i = a.getInstruction();
        }
        if (i != null) {
            int spaceIndex = input.getValue().lastIndexOf(" ");
            if (spaceIndex != -1) {
                if(i instanceof Parameterizable) {
                    Parameterizable p = (Parameterizable) i;
                    String parameter = input.getValue().substring(spaceIndex + 1).trim();
                    p.setParameter(Integer.parseInt(parameter));
                }else
                    throw new InvalidParametersException(input.getValue().substring(0, input.getValue().lastIndexOf(" ")).trim() + " instruction does not have a parameter", input.getLineNr());
            }
            if(spaceIndex==-1 && i instanceof Parameterizable)
                throw new InvalidParametersException(input.getValue() +" instruction needs a parameter", input.getLineNr());
            return i;
        } else
            throw new WrongSyntaxException(input.getValue() + " is not a valid instruction", input.getLineNr());
    }
}
