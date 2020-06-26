package ch.supsi.teencpu.models.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApplicationExceptionTest {

    @Test
    public void applicationExceptionTest() {
        CompilerException ex1 = new CompilerException("Test...", 12);
        ApplicationException ex0;
        try {
            throw ex1;
        } catch (ApplicationException test) {
            ex0 = test;
        }
        assertEquals(ex0, (ApplicationException) ex1);
        assertEquals(ex0.getLineNr(), ex1.getLineNr());
        RunTimeException ex2 = new RunTimeException("Test...", 12);
        try {
            throw ex2;
        } catch (ApplicationException test) {
            ex0 = test;
        }
        assertEquals(ex0, (ApplicationException) ex2);
        assertEquals(ex0.getLineNr(), ex2.getLineNr());
    }
}
