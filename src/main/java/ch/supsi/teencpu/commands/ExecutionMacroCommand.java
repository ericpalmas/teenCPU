package ch.supsi.teencpu.commands;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.services.ExecutorService;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.List;
public class ExecutionMacroCommand implements MacroCommand {
    private List<? extends Command> commands;
    private DoubleProperty delay = new SimpleDoubleProperty(0);
    private  ExecutorService executorService = ExecutorService.getInstance();
    public static boolean firstExecution = true;
    @Override
    public void execute() throws ApplicationException {
        if(firstExecution){
            executorService.hiddenExecution();
            firstExecution = false;
        }
        for(int i=0;i<ExecutorService.getInstance().getEnd();i++){
            executorService.execute();
        }
    }
    @Override
    public void executeNext() throws ApplicationException {
        if (firstExecution) {
            executorService.hiddenExecution();
            firstExecution = false;
        }
        executorService.execute();
    }
    @Override
    public void executePrevious() throws ApplicationException {
        executorService.unexecute();
    }
    @Override
    public void setCommands(List<? extends Command> commands) {
        this.commands = commands;
    }
    public void bindDelay(DoubleProperty delay) {
        this.delay.bind(delay);
    }
    public List<? extends Command> getCommands() {
        return commands;
    }
}