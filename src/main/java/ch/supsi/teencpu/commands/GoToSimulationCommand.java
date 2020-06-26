package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.MainController;

public class GoToSimulationCommand implements Command {

    private MainController mainController;

    public GoToSimulationCommand(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void execute() {
        mainController.goToSimulation();
    }
}
