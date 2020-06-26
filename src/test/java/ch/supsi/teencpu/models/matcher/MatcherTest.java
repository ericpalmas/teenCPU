package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.exceptions.NoParametersException;
import ch.supsi.teencpu.models.exceptions.NullParameterException;
import ch.supsi.teencpu.models.exceptions.WrongSyntaxException;
import ch.supsi.teencpu.models.instruction.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


public class MatcherTest {

    @Test
    public void matcherTest() {
        Association duplicatingAssociation = Association.createAssociation(new DuplicatingInstruction(), "duplicate(\\s*)(\\d*)");
        Association jumpAssociation = Association.createAssociation(new JumpInstruction(), "jump(\\s+)(\\d*)");
        Association sumAssociation = Association.createAssociation(new SumInstruction(), "sum(\\s*)(\\d*)");
        String input = "duplicate;\njump 32;\nsum;";
        Lines lines = null;
        try {
            lines = Lines.createLines(input, ";");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        lines.generateInputLines();
        List<Association> associations = new ArrayList<>();
        associations.add(duplicatingAssociation);
        associations.add(jumpAssociation);
        associations.add(sumAssociation);
        assertTrue("duplicate".matches(duplicatingAssociation.getMatcher()));
        assertTrue("jump 32".matches(jumpAssociation.getMatcher()));
        assertTrue("sum".matches(sumAssociation.getMatcher()));
        Matcher m = Matcher.createMatcher(lines, associations);
        try {
            m.getLines().printLines();
            List<Instruction> list = m.getInstructions();
            assertEquals(list.get(0).getClass(), DuplicatingInstruction.class);
            assertEquals(list.get(1).getClass(), JumpInstruction.class);
            Parameterizable p = (Parameterizable) list.get(1);
            assertEquals(p.getParameter(), 32);
            assertEquals(list.get(2).getClass(), SumInstruction.class);
        } catch (WrongSyntaxException w) {
            w.printStackTrace();
        }
    }

    @Test
    public void matcherBug1Test() {
        Association stackingAssociation = Association.createAssociation(new StackingInstruction(), "impila(\\s*)(\\d*)");
        String input = "impila 12;\nimpila 32;\nimpila 42;";
        Lines lines = null;
        try {
            lines = Lines.createLines(input, ";");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        lines.generateInputLines();
        List<Association> associations = new ArrayList<>();
        associations.add(stackingAssociation);
        assertTrue("impila".matches(stackingAssociation.getMatcher()));
        Matcher m = Matcher.createMatcher(lines, associations);
        try {
            m.getLines().printLines();
            Parameterizable p;
            List<Instruction> list = m.getInstructions();
            assertEquals(list.get(0).getClass(), StackingInstruction.class);
            p = (Parameterizable) list.get(0);
            assertEquals(p.getParameter(), 12);
            assertEquals(list.get(1).getClass(), StackingInstruction.class);
            p = (Parameterizable) list.get(1);
            assertEquals(p.getParameter(), 32);
            assertEquals(list.get(2).getClass(), StackingInstruction.class);
            p = (Parameterizable) list.get(2);
            assertEquals(p.getParameter(), 42);
        } catch (WrongSyntaxException w) {
            w.printStackTrace();
        }
    }

    @Test
    public void parameterTest() {
        Association jumpAssociation = Association.createAssociation(new JumpInstruction(), "jump"+"(\\s*)(\\d*)");

        Association readAssociation = Association.createAssociation(new ReadingInstruction(), "read"+"(\\s*)(\\d*)");
        String input="read 48;\njump ;";
        Lines lines = null;
        try {
            lines = Lines.createLines(input, ";");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        lines.generateInputLines();
        List<Association> associations = new ArrayList<>();
        associations.add(jumpAssociation);
        associations.add(readAssociation);
        Matcher m = Matcher.createMatcher(lines, associations);
        try {
            m.getLines().printLines();
            List<Instruction> list = m.getInstructions();
            fail();
        } catch (WrongSyntaxException w) {
           // w.printStackTrace();
        }
    }
}


