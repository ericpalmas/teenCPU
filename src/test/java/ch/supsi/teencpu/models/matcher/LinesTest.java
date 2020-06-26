package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.exceptions.NullParameterException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class LinesTest {

    @Test
    public void linesTest() {
        String input = "I;am;\ntesting\n\n";
        String splitter = ";";
        Lines l = null;
        try {
            l = Lines.createLines(input, splitter);
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        l.generateInputLines();
        assertEquals(l.getInputLines().size(),3);
        assertEquals(l.getInput(), input);
        assertEquals(l.getSplitter(), splitter);
        assertEquals(l.getInputLines().get(0).getValue(), "I");
        assertEquals(l.getInputLines().get(1).getValue(), "am");
        assertNotEquals(l.getInputLines().get(1).getValue(), ";");
        assertEquals(l.getInputLines().get(2).getValue(), "testing");
        assertEquals(l.getInputLines().get(0).getLineNr(), 1);
        assertEquals(l.getInputLines().get(1).getLineNr(), 1);
        assertEquals(l.getInputLines().get(2).getLineNr(), 2);
        l.setSplitter("am");
        l.generateInputLines();
        assertEquals(l.getInputLines().get(0).getValue(), "I;");
        assertEquals(l.getInputLines().get(1).getValue(), ";testing");
        assertEquals(l.getInputLines().get(0).getLineNr(), 1);
        assertEquals(l.getInputLines().get(1).getLineNr(), 2);
        assertTrue("()".matches("\\(\\d*\\)"));
    }

}
