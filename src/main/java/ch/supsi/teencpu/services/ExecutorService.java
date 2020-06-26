package ch.supsi.teencpu.services;
import ch.supsi.teencpu.commands.ExecuteInstructionCommand;
import ch.supsi.teencpu.commands.ExecutionMacroCommand;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.undoRedo.CareTaker;
import ch.supsi.teencpu.observers.Observer;
public class ExecutorService {
    private static ExecutorService service;
    private Program program;
    private Observer stackObserver;
    private Observer outputObserver;
    private boolean endSimulation = false;
    private int end;
    private CareTaker careTaker;
    private ExecutorService() {
    }
    public int getEnd() {
        return end;
    }
    public static ExecutorService getInstance() {
        if (service == null)
            service = new ExecutorService();
        return service;
    }
    public void setExecutionParameters(Program program, Observer outputObserver, Observer stackObserver) {
        this.program = program;
        this.outputObserver = outputObserver;
        this.stackObserver = stackObserver;
        this.careTaker = new CareTaker();
        ExecutionMacroCommand.firstExecution = true;
    }
    public void execute() throws ApplicationException {
        program.getStateFromMemento(careTaker.getNextState());
        stackObserver.update(program.getStack());
        outputObserver.update(program.getOutput());
    }
    public void unexecute() throws ApplicationException {
        program.getStateFromMemento(careTaker.getPreviousState());
        stackObserver.update(program.getStack());
        outputObserver.update(program.getOutput());
    }
    public void hiddenExecution() throws ApplicationException {
        careTaker = new CareTaker();
        Program testProgram = new Program(program.deepCopyInstructions(), program.getStack().deepCopy(), program.getInput().deepCopy(), program.getOutput().deepCopy(), program.getProgramCounter().deepCopy());
        careTaker.add(testProgram.saveStateToMemento());
        boolean endSimulation = false;
        while (!endSimulation) {
                endSimulation = testProgram.getInstructions().get(testProgram.getProgramCounter().getValue().get()).execute(testProgram);
                careTaker.add(testProgram.saveStateToMemento());
                end++;
        }
        careTaker.resetStep();
    }
    public Program getProgram() {
        return program;
    }
    public Observer getStackObserver() {
        return stackObserver;
    }
    public Observer getOutputObserver() {
        return outputObserver;
    }
}