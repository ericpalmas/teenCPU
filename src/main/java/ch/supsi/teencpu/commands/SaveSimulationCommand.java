package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.SimulationController;
import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.repository.Repository;
import ch.supsi.teencpu.repository.Simulation;

import java.util.List;

public class SaveSimulationCommand implements Command {

    private Repository repo;
    private SimulationController simulationController;

    public SaveSimulationCommand(Repository repo, SimulationController simulationController) {
        this.repo = repo;
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        String[] instructions = simulationController.getCodeArea().getText().split("(;+\n*)");
        Simulation simulation = Simulation.
                newSimulation(new Input(simulationController.getInputTextField().getText()), List.of(instructions));
        repo.saveSimulation(simulation);
        simulationController.getSavedSimulationView().getTheController().loadAllsimulation();
    }
}
