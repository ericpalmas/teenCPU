package ch.supsi.teencpu.models.stack;

import ch.supsi.teencpu.observers.Observer;
import ch.supsi.teencpu.observers.Subject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Stack;

public class AppStack implements Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    private Stack<IntegerProperty> stack = new Stack<>();

    public AppStack() {}

    public Stack<IntegerProperty> getStack() {
        return stack;
    }

    public void push(int value) {
        if(stack.size()<=5)
          stack.push(new SimpleIntegerProperty(value));
        notifyObserver();
    }
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public int pop() {
        int i = -1;
        if(stack.size()>0)
           i = stack.pop().get();
        notifyObserver();
        return i;
    }

    public int size() {
        return stack.size();
    }

    public IntegerProperty get(int i) {
        return stack.get(i);
    }

    public void set(int i, IntegerProperty val) {
        stack.set(i, val);
    }

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

    public AppStack deepCopy() {
        AppStack appStack = new AppStack();
        for (IntegerProperty value : this.stack) {
            appStack.stack.push(value);
        }
        return appStack;
    }

    @Override
    public String toString() {
        return "AppStack{" +
                "observers=" + observers +
                ", stack=" + stack +
                '}';
    }
}
