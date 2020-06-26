package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.instruction.DuplicatingInstruction;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class AssociationTest {

    @Test
    public void associationTest() {
        DuplicatingInstruction d = new DuplicatingInstruction();
        String matcher = "duplicate()";
        Association association = Association.createAssociation(d, matcher);
        assertEquals(association.getInstruction().getClass(), d.getClass());
        assertEquals(association.getMatcher(), matcher);
    }

}
