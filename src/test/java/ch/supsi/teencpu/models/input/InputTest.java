package ch.supsi.teencpu.models.input;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputTest {

    @Test
    public void checkGetCurrentValue() {
        Input input = new Input("Ciao");
        assertEquals('C', input.getCurrentValue());
        assertEquals('i', input.getCurrentValue());
        assertEquals(2, input.size());
        assertEquals("Ciao", input.getValue());
    }

    @Test
    public void checkEquals(){
        Input input1 = new Input("Ciao");
        Input input2 = input1.deepCopy();
        assertEquals("Ciao",input2.getValue());
    }
    @Test
    public void cheSetter(){
        Input input1 = new Input();
        input1.setValue("Ciao");
        assertEquals("Ciao",input1.getValue());
    }
    @Test
    public void checkComeBack(){
        Input input1 = new Input();
        input1.setValue("Ciao");
        input1.getCurrentValue();
        assertEquals('i',input1.getCurrentValue());
        input1.comeBack((char)34);
        input1.comeBack((char)34);
        assertEquals('C',input1.getCurrentValue());

    }
}