package ch.supsi.teencpu.models.undoRedo;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {


    @Override
    public String toString() {
        return "CareTaker{" +
                "mementoList=" + mementoList +
                '}';
    }

    private List<Memento> mementoList = new ArrayList<>();

    private int step = mementoList.size() - 1;

    public void add(Memento state) {
        mementoList.add(state);
        step++;
    }

    public Memento getPreviousState() {
        if (step > 0) {
            step--;
            return mementoList.get(step);
        } else {
            return mementoList.get(0);
        }

    }

    public int size(){
        return mementoList.size();
    }

    public void resetStep(){
        step = 0;
    }

    public Memento getNextState() {
        if(step < mementoList.size()-1){
            step++;
            return mementoList.get(step);
        }else{
            return mementoList.get(mementoList.size()-1);
        }
    }

    public Memento getCurrentState() {
        if(step<0){
            return mementoList.get(0);
        }else if (step>=mementoList.size()){
            return mementoList.get(mementoList.size()-1);
        } else{
            return mementoList.get(step);
        }
    }


    public void reset(){
        step = 0;
        mementoList = new ArrayList<>();

    }
}
