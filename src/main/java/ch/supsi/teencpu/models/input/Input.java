package ch.supsi.teencpu.models.input;

public class Input{
    private String value;

    // exclude fields annotated with transient from json serialization
    private transient int currentPosition;
    private transient int size;

    public Input(String value) {
        this.value = value;
        currentPosition = 0;
        size = value.length();
    }
    public Input(){}

    public void setValue(String value) {
        this.value = value;
        size = value.length();
    }

    public String getValue() {
        return value;
    }

    public int size() {
        return size;
    }

    public char getCurrentValue() {
        char val = 0;
        if(size > 0)
        val = value.charAt(currentPosition);
        size--;
        currentPosition++;
        return val;
    }
    public void comeBack(char val){
        if(val!=0){
            size++;
            currentPosition--;
        }
    }
    public Input deepCopy(){
        Input newInput = new Input(this.getValue());
        newInput.currentPosition = this.currentPosition;
        newInput.size = this.size;
        return newInput;
    }
}
