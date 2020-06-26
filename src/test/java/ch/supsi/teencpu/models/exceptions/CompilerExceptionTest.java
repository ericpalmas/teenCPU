package ch.supsi.teencpu.models.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CompilerExceptionTest {

    @Test
    public void applicationExceptionTest() {
        CompilerException ex1 = new CompilerException("Test...", 12);
        assertEquals(ex1.getLineNr(), 12);
    }

}
