package ch.supsi.teencpu.models.program;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.stack.AppStack;
import ch.supsi.teencpu.models.undoRedo.Memento;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import java.util.ArrayList;
import java.util.List;
public class Program {
    private List<Instruction> instructions;
    private AppStack stack;
    private Input input;
    private Output output;
    private ProgramCounter programCounter;
    public Program(List<Instruction> instructions, AppStack stack, Input input, Output output, ProgramCounter programCounter) {
        this.instructions = instructions;
        this.stack = stack;
        this.input = input;
        this.output = output;
        this.programCounter = programCounter;
    }
    public Program(){
        this.instructions = new ArrayList<>();
        this.stack = new AppStack();
        this.input = new Input();
        this.output = new Output();
        this.programCounter = new ProgramCounter();
    }
    public List<Instruction> getInstructions() {
        return instructions;
    }
    public AppStack getStack() {
        return stack;
    }
    public void setStack(AppStack stack) {
        this.stack = stack;
    }
    public Input getInput() {
        return input;
    }
    public void setInput(Input input) {
        this.input = input;
    }
    public Output getOutput() {
        return output;
    }
    public void setOutput(Output output) {
        this.output = output;
    }
    public ProgramCounter getProgramCounter() {
        return programCounter;
    }
    public void setProgramCounter(ProgramCounter programCounter) {
        this.programCounter = programCounter;
    }
    public void setProgramCounter(int programCounter){
        this.programCounter.set(programCounter);
    }
    public Memento saveStateToMemento() {
        return new Memento(stack.deepCopy(), programCounter.deepCopy(), output.deepCopy(), input.deepCopy());
    }
    public void getStateFromMemento(Memento memento) {
        stack = memento.getStack().deepCopy();
        input = memento.getInput().deepCopy();
        output = memento.getOutput().deepCopy();
        setProgramCounter(memento.getProgramCounter().getValue().get());
    }
    public ArrayList<Instruction> deepCopyInstructions() {
        ArrayList<Instruction> instructions = new ArrayList<>(this.instructions);
        return instructions;
    }
}