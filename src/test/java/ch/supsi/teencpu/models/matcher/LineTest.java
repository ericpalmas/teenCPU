package ch.supsi.teencpu.models.matcher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void lineTest() {
        Line l = new Line("test", 25);
        assertEquals(l.getValue(), "test");
        assertEquals(l.getLineNr(), 25);
    }
}
