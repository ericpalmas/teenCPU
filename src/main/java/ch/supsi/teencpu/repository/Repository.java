package ch.supsi.teencpu.repository;

import java.util.List;
import java.util.UUID;

public interface Repository {

    boolean saveSimulation(Simulation simulation);

    boolean removeSimulation(Simulation simulation);

    Simulation getSimulation(UUID id) throws RepositoryException;

    List<Simulation> getAllSimulations() throws RepositoryException;

}
