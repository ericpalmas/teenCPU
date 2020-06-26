package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.SimulationController;
import ch.supsi.teencpu.repository.Simulation;

public class OpenSSCommand implements Command {

    private SimulationController simulationController;

    public OpenSSCommand(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        Simulation simulation = simulationController.getSavedSimulationView().getTheController().getSelectedSimulation();
        if (simulation != null)
            simulationController.openSelectedSimulation(simulation);
    }

}
