package ch.supsi.teencpu.repository;

import ch.supsi.teencpu.path.JsonPathManager;
import ch.supsi.teencpu.path.PathManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JsonRepository implements Repository {

    private static JsonRepository jsonRepository;
    private Type simulationType = new TypeToken<List<Simulation>>() {
    }.getType();
    private PathManager jsonPathManager;
    private Gson gson;

    private JsonRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        jsonPathManager = new JsonPathManager();
    }

    public static JsonRepository getInstance() {
        if (jsonRepository == null)
            return new JsonRepository();
        return jsonRepository;
    }


    @Override
    public boolean saveSimulation(Simulation simulation) {
        try {
            List<Simulation> savedSimulations = getAllSimulations();
            if (!savedSimulations.contains(simulation))
                savedSimulations.add(simulation);
            saveAllSimulations(savedSimulations);
            return true;
        } catch (JsonNotFoundException e) {
            return false;
        }
    }

    @Override
    public Simulation getSimulation(UUID id) throws RepositoryException {
        Optional<Simulation> optionalSimulation = getAllSimulations().stream()
                .filter(simulation -> simulation.getId().equals(id))
                .findFirst();
        if (optionalSimulation.isPresent())
            return optionalSimulation.get();
        throw new SimulationDoesntExistException();
    }

    @Override
    public List<Simulation> getAllSimulations() throws JsonNotFoundException {
        try (Reader fileReader = new FileReader(jsonPathManager.getPath())) {
            List<Simulation> simulations = gson.fromJson(fileReader, simulationType);
            fileReader.close();
            if (simulations == null)
                return new ArrayList<>();
            return simulations;
        } catch (IOException e) {
            throw new JsonNotFoundException();
        }
    }

    @Override
    public boolean removeSimulation(Simulation simulation) {
        try {
            List<Simulation> savedSimulations = getAllSimulations();
            boolean removed = savedSimulations.remove(simulation);
            if (removed) {
                saveAllSimulations(savedSimulations);
                return true;
            }
            return false;
        } catch (JsonNotFoundException e) {
            return false;
        }
    }

    private void saveAllSimulations(List<Simulation> simulations) {
        try {
            Writer fileWriter = new FileWriter(jsonPathManager.getPath());
            gson.toJson(simulations, simulationType, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
