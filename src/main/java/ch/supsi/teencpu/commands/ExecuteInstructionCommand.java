package ch.supsi.teencpu.commands;


import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import ch.supsi.teencpu.services.ExecutorService;

public class ExecuteInstructionCommand implements Command {

    private ExecutorService executorService;
    private Output output;
    private AppStack appStack;
    private ProgramCounter pc;


    public ExecuteInstructionCommand(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void execute() throws ApplicationException {
        executorService.execute();
        setCurrentSate();
    }

    public void unexecute() throws ApplicationException {
        executorService.unexecute();
        setCurrentSate();
    }

    public void setCurrentSate() {
        output = executorService.getProgram().getOutput().deepCopy();
        appStack = executorService.getProgram().getStack().deepCopy();
        pc = executorService.getProgram().getProgramCounter().deepCopy();

        System.out.println(output);
        System.out.println(appStack);
        System.out.println(pc);
        System.out.println();
    }

    public void applyCurrentState() throws ApplicationException {
        executorService.execute();
        executorService.getOutputObserver().update(output);
        executorService.getStackObserver().update(appStack);
        executorService.getProgram().setProgramCounter(pc);

    }

    public Output getOutput() {
        return output;
    }

    public AppStack getAppStack() {
        return appStack;
    }

    public ProgramCounter getPc() {
        return pc;
    }

    @Override
    public String toString() {
        return "ExecuteInstructionCommand{" +
                "executorService=" + executorService +
                ", output=" + output +
                ", appStack=" + appStack +
                ", pc=" + pc +
                '}';
    }
}
