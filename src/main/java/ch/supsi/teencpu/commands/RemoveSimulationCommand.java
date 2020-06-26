package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.SavedSimulationsController;
import ch.supsi.teencpu.repository.JsonRepository;
import ch.supsi.teencpu.repository.Repository;
import ch.supsi.teencpu.repository.Simulation;

public class RemoveSimulationCommand implements Command {

    private Repository repo = JsonRepository.getInstance();
    private SavedSimulationsController ssController;

    public RemoveSimulationCommand(SavedSimulationsController ssController) {
        this.ssController = ssController;
    }

    @Override
    public void execute() {
        Simulation simulation = ssController.getSelectedSimulation();
        if (simulation != null)
            repo.removeSimulation(simulation);
        ssController.loadAllsimulation();
    }
}
