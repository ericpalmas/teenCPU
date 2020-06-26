package ch.supsi.teencpu.repository;

import ch.supsi.teencpu.models.input.Input;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Simulation {

    private final UUID id;
    private final LocalDateTime creationDate;
    private Input input;
    private List<String> instructions;


    public Simulation(Input input, List<String> instructions) {
        this.id = UUID.randomUUID();
        creationDate = LocalDateTime.now();
        this.input = input;
        this.instructions = instructions;
    }

    public static Simulation newSimulation(Input input, List<String> instructions) {
        return new Simulation(input, instructions);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simulation that = (Simulation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", input=" + input +
                ", instructions=" + instructions +
                '}';
    }
}
