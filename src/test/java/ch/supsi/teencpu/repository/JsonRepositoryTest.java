package ch.supsi.teencpu.repository;

import ch.supsi.teencpu.models.input.Input;
import ch.supsi.teencpu.path.ApplicationPath;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class JsonRepositoryTest {

    private Repository repository;
    private Input input;
    private List<String> instructions;

    @Before
    public void init() {
        ApplicationPath.initApplicationPath();
        repository = JsonRepository.getInstance();
        input = new Input("ABCDEFH");
        instructions = Arrays.asList("leggi", "leggi",  "leggi", "leggi", "leggi",
                "scrivi", "scrivi","scrivi","scrivi","scrivi");
    }

    @Test
    public void saveSimulation() {
        Simulation sim1 = new Simulation(input, instructions);
        assertTrue(repository.saveSimulation(sim1));
        try {
            assertEquals(sim1, repository.getSimulation(sim1.getId()));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSimulation() {
        Simulation sim1 = new Simulation(input, instructions);
        try {
            repository.getSimulation(sim1.getId());
            fail();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        repository.saveSimulation(sim1);
        try {
            repository.getSimulation(sim1.getId());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllSimulations() {
        try {
            assertFalse(repository.getAllSimulations().isEmpty());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeSimulation() {
        Simulation sim = new Simulation(input, instructions);
        repository.saveSimulation(sim);
        assertTrue(repository.removeSimulation(sim));
        try {
            repository.getSimulation(sim.getId());
            fail();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}