package ch.supsi.teencpu.presentation;


import ch.supsi.teencpu.repository.Simulation;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimulationPresenter implements Presentable<Simulation> {


    @Override
    public String present(Simulation simulation) {
        return "Input: " + simulation.getInput().getValue() + "\n"
                + simulation.getInstructions() + "\n"
                + simulation.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd - HH:mm"));
    }

    public String presentInListCell(Simulation simulation) {
        return "Input: " + simulation.getInput().getValue() + "\n"
                + presentInstructions(simulation.getInstructions()) + "\n"
                + simulation.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd - HH:mm"));
    }

    private String presentInstructions(List<String> instructions) {
        int size = instructions.size();
        if (size < 5) {
            return instructions.toString();
        }
        return instructions.toString().substring(0, 40) + " ......";
    }
}
