package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.dialogs.AppToolTip;
import ch.supsi.teencpu.javafx.jfxcontrollers.SimulationController;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import ch.supsi.teencpu.models.exceptions.CompilerException;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.output.Output;
import ch.supsi.teencpu.models.program.Program;
import ch.supsi.teencpu.models.programCounter.ProgramCounter;
import ch.supsi.teencpu.models.stack.AppStack;
import ch.supsi.teencpu.observers.Observer;
import ch.supsi.teencpu.observers.OutputObserver;
import ch.supsi.teencpu.observers.StackObserver;
import ch.supsi.teencpu.services.CompilerService;
import ch.supsi.teencpu.services.ExecutorService;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;

public class InitNewSimulationCommand implements Command {

    private ExecutorService executorService;
    private SimulationController controller;
    private CompilerService compilerService;
    private ResourceBundle resourceBundle;
    private List<Instruction> instructions;
    private final String SPLITTER = ";";

    public InitNewSimulationCommand(ExecutorService executorService, SimulationController controller, CompilerService compilerService, ResourceBundle resourceBundle) {
        this.executorService = executorService;
        this.controller = controller;
        this.compilerService = compilerService;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public void execute() throws CompilerException {
        Observer outputObserver = new OutputObserver(controller.getOutputHBox());
        Observer stackObserver = new StackObserver(controller.getStackVBox());
        AppStack appStack = new AppStack();
        Input input = new Input(controller.getInputTextField().getText());
        Output output = new Output();
        ProgramCounter pc = new ProgramCounter();

        appStack.register(stackObserver);
        output.register(outputObserver);
        compilerService.initializeMatcher(controller.getCodeArea().getText(), SPLITTER, resourceBundle);
            try {
                instructions = compilerService.getInstructions();
                Program program = new Program(instructions, appStack, input, output, pc);
                executorService.setExecutionParameters(program, outputObserver, stackObserver);
                controller.getPcValueLbl().textProperty().bind(executorService.getProgram().getProgramCounter().getValue().asString());
            } catch (ApplicationException e) {
                controller.getMsgBtn().setDisable(false);
                AppToolTip.showTooltip((Stage) controller.getRoot().getScene().getWindow(), controller.getMsgBtn(), e.getMessage());
                throw new CompilerException(e.getMessage(), e.getLineNr());
            }
    }
}
