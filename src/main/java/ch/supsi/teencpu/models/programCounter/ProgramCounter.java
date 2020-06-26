package ch.supsi.teencpu.models.programCounter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ProgramCounter {

    private IntegerProperty value;

    public ProgramCounter(int value) {
        this.value = new SimpleIntegerProperty();
        this.value.set(value);
    }
    public ProgramCounter() {
        this.value = new SimpleIntegerProperty();
        this.value.set(0);
    }
    public IntegerProperty getValue() {
        return value;
    }
    public void increase() {
        value.set(value.get() + 1);
    }
    public void reset() {
        value.set(0);
    }
    public void set(int value) {
        this.value.set(value);
    }
    public ProgramCounter deepCopy() {
        return new ProgramCounter(this.getValue().get());
    }
    @Override
    public String toString() {
        return "ProgramCounter{" +
                "value=" + value +
                '}';
    }
}