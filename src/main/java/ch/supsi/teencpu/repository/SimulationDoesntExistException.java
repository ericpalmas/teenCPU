package ch.supsi.teencpu.repository;

public class SimulationDoesntExistException extends RepositoryException {

    public SimulationDoesntExistException() {
        super("The requested simulation doesn't exist");
    }
}
