package ch.supsi.teencpu.services;

import ch.supsi.teencpu.models.instruction.*;
import ch.supsi.teencpu.models.matcher.Association;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssociationInitializer {

    public static List<Association> getAssociationsList(ResourceBundle resources) {
        List<Association> list = new ArrayList<>();
        Association duplicatingAssociation = Association.createAssociation(new DuplicatingInstruction(),resources.getString("double")+"(\\s*)(\\d*)");
        Association stackingAssociation = Association.createAssociation(new StackingInstruction(), resources.getString("push") + "(\\s*)(\\d*)");
        Association readingAssociation = Association.createAssociation(new ReadingInstruction(), resources.getString("read")+"(\\s*)(\\d*)");
        Association jumpAssociation = Association.createAssociation(new JumpInstruction(), resources.getString("jump") + "(\\s*)(\\d*)");
        Association jumpIfZeroAssociation = Association.createAssociation(new JumpIfZeroInstruction(), resources.getString("jumpifzero") + "(\\s*)(\\d*)");
        Association writingAssociation = Association.createAssociation(new WritingInstruction(), resources.getString("write")+"(\\s*)(\\d*)");
        Association sumAssociation = Association.createAssociation(new SumInstruction(), resources.getString("sum")+"(\\s*)(\\d*)");
        Association subtractAssociation = Association.createAssociation(new SubtractInstruction(), resources.getString("subtract")+"(\\s*)(\\d*)");
        Association removingAssociation = Association.createAssociation(new RemoveInstruction(), resources.getString("pop")+"(\\s*)(\\d*)");
        Association terminateAssociation = Association.createAssociation(new TerminateInstruction(), resources.getString("terminate")+"(\\s*)(\\d*)");
        list.add(duplicatingAssociation);
        list.add(stackingAssociation);
        list.add(readingAssociation);
        list.add(jumpAssociation);
        list.add(jumpIfZeroAssociation);
        list.add(writingAssociation);
        list.add(sumAssociation);
        list.add(subtractAssociation);
        list.add(removingAssociation);
        list.add(terminateAssociation);
        return list;
    }
}