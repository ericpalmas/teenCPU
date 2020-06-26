package ch.supsi.teencpu.models.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RunTimeExceptionTest {

    @Test
    public void runTimeExceptionTest() {
        RunTimeException ex1 = new RunTimeException("Test...", 12);
        assertEquals(ex1.getLineNr(), 12);
    }

}
