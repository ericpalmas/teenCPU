package ch.supsi.teencpu.models.undoRedo;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;

public class Memento {

    private AppStack stack;
    private ProgramCounter programCounter;
    private Output output;
    private Input input;

    public Memento(AppStack stack, ProgramCounter programCounter, Output output, Input input) {
        this.stack = stack;
        this.programCounter = programCounter;
        this.output = output;
        this.input = input;
    }

    public AppStack getStack() {
        return stack;
    }

    public Input getInput() {
        return input;
    }

    public Output getOutput() {
        return output;
    }

    public ProgramCounter getProgramCounter() {
        return programCounter;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "stack=" + stack +
                ", programCounter=" + programCounter +
                ", output=" + output +
                ", input=" + input +
                '}';
    }
}
