package ch.supsi.teencpu.services;

import ch.supsi.teencpu.models.instruction.*;
import ch.supsi.teencpu.models.matcher.Association;
import org.junit.Test;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class AssociationsInitializerTest {

    @Test
    public void associationsInitializerTestItalian() {
        Locale.setDefault(Locale.ITALIAN);
        ResourceBundle r = ResourceBundle.getBundle("i18n/strings");
        List<Association> associationsList = AssociationInitializer.getAssociationsList(r);
        assertEquals(associationsList.get(0).getInstruction().getClass(), DuplicatingInstruction.class);
        assertEquals(associationsList.get(1).getInstruction().getClass(), StackingInstruction.class);
        assertEquals(associationsList.get(2).getInstruction().getClass(), ReadingInstruction.class);
        assertEquals(associationsList.get(3).getInstruction().getClass(), JumpInstruction.class);
        assertEquals(associationsList.get(4).getInstruction().getClass(), JumpIfZeroInstruction.class);
        assertEquals(associationsList.get(5).getInstruction().getClass(), WritingInstruction.class);
        assertEquals(associationsList.get(6).getInstruction().getClass(), SumInstruction.class);
        assertEquals(associationsList.get(7).getInstruction().getClass(), SubtractInstruction.class);
        assertEquals(associationsList.get(8).getInstruction().getClass(), RemoveInstruction.class);
        assertEquals(associationsList.get(9).getInstruction().getClass(), TerminateInstruction.class);
        assertEquals("duplica(\\s*)(\\d*)", associationsList.get(0).getMatcher());
        assertEquals("impila(\\s*)(\\d*)", associationsList.get(1).getMatcher());
        assertEquals("leggi(\\s*)(\\d*)", associationsList.get(2).getMatcher());
        assertEquals("salta(\\s*)(\\d*)", associationsList.get(3).getMatcher());
        assertEquals("saltasezero(\\s*)(\\d*)", associationsList.get(4).getMatcher());
        assertEquals("scrivi(\\s*)(\\d*)", associationsList.get(5).getMatcher());
        assertEquals("somma(\\s*)(\\d*)", associationsList.get(6).getMatcher());
        assertEquals("sottrai(\\s*)(\\d*)", associationsList.get(7).getMatcher());
        assertEquals("togli(\\s*)(\\d*)", associationsList.get(8).getMatcher());
        assertEquals("termina(\\s*)(\\d*)", associationsList.get(9).getMatcher());
    }


    @Test
    public void associationsInitializerTestEnglish() {
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle r = ResourceBundle.getBundle("i18n/strings");
        List<Association> associationsList = AssociationInitializer.getAssociationsList(r);
        assertEquals(associationsList.get(0).getInstruction().getClass(), DuplicatingInstruction.class);
        assertEquals(associationsList.get(1).getInstruction().getClass(), StackingInstruction.class);
        assertEquals(associationsList.get(2).getInstruction().getClass(), ReadingInstruction.class);
        assertEquals(associationsList.get(3).getInstruction().getClass(), JumpInstruction.class);
        assertEquals(associationsList.get(4).getInstruction().getClass(), JumpIfZeroInstruction.class);
        assertEquals(associationsList.get(5).getInstruction().getClass(), WritingInstruction.class);
        assertEquals(associationsList.get(6).getInstruction().getClass(), SumInstruction.class);
        assertEquals(associationsList.get(7).getInstruction().getClass(), SubtractInstruction.class);
        assertEquals(associationsList.get(8).getInstruction().getClass(), RemoveInstruction.class);
        assertEquals(associationsList.get(9).getInstruction().getClass(), TerminateInstruction.class);
        assertEquals("double(\\s*)(\\d*)", associationsList.get(0).getMatcher());
        assertEquals("push(\\s*)(\\d*)", associationsList.get(1).getMatcher());
        assertEquals("read(\\s*)(\\d*)", associationsList.get(2).getMatcher());
        assertEquals("jump(\\s*)(\\d*)", associationsList.get(3).getMatcher());
        assertEquals("jumpifzero(\\s*)(\\d*)", associationsList.get(4).getMatcher());
        assertEquals("write(\\s*)(\\d*)", associationsList.get(5).getMatcher());
        assertEquals("sum(\\s*)(\\d*)", associationsList.get(6).getMatcher());
        assertEquals("subtract(\\s*)(\\d*)", associationsList.get(7).getMatcher());
        assertEquals("pop(\\s*)(\\d*)", associationsList.get(8).getMatcher());
        assertEquals("terminate(\\s*)(\\d*)", associationsList.get(9).getMatcher());
    }
}
