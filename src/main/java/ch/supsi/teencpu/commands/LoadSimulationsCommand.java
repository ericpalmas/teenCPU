package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.SavedSimulationsController;
import ch.supsi.teencpu.repository.JsonRepository;
import ch.supsi.teencpu.repository.Repository;
import ch.supsi.teencpu.repository.RepositoryException;


public class LoadSimulationsCommand implements Command {

    private SavedSimulationsController ssController;
    private Repository repo = JsonRepository.getInstance();

    public LoadSimulationsCommand(SavedSimulationsController ssController) {
        this.ssController = ssController;
    }

    @Override
    public void execute() {
        try {
            ssController.getObservSimulations().setAll(repo.getAllSimulations());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


}
