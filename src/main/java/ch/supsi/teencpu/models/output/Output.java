package ch.supsi.teencpu.models.output;

import ch.supsi.teencpu.observers.Observer;
import ch.supsi.teencpu.observers.Subject;

import java.util.ArrayList;

public class Output implements Subject,Cloneable {
    private ArrayList<Observer> observers = new ArrayList<>();

    private StringBuilder output = new StringBuilder();

    public String getOutput() {
        return output.toString();
    }

    public void appendValue(char value) {
        output.append(value);
        notifyObserver();
    }

    public char getLastValue(){
        char removed = output.charAt(output.length()-1);
        output.deleteCharAt(output.length()-1);
        return removed;
    }

    public ArrayList<Observer> getObservers() { return observers; }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        System.out.println("Observer " + (observerIndex + 1) + " deleted");
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public Output deepCopy() {
        Output newOutput = new Output();
        for (int i = 0; i < output.length(); i++) {
            newOutput.appendValue(output.charAt(i));
        }
        return newOutput;
    }

    @Override
    public String toString() {
        return "Output{" +
                "observers=" + observers +
                ", output=" + output +
                '}';
    }
}
