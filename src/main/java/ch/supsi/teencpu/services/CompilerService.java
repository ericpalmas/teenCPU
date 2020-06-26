package ch.supsi.teencpu.services;

import ch.supsi.teencpu.models.exceptions.NullParameterException;
import ch.supsi.teencpu.models.exceptions.WrongSyntaxException;
import ch.supsi.teencpu.models.instruction.Instruction;
import ch.supsi.teencpu.models.matcher.Lines;
import ch.supsi.teencpu.models.matcher.Matcher;

import java.util.List;
import java.util.ResourceBundle;

public class CompilerService {

    private static CompilerService compilerInstance = null;

    private Matcher matcher;

    private CompilerService() {
    }

    // static method to create instance of Singleton class
    public static CompilerService getInstance() {
        if (compilerInstance == null)
            compilerInstance = new CompilerService();
        return compilerInstance;
    }

    public void initializeMatcher(String instructionInput, String splitter, ResourceBundle resources) {
        Lines l = null;

        try {
            l = Lines.createLines(instructionInput, splitter);
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        this.matcher = Matcher.createMatcher(l, AssociationInitializer.getAssociationsList(resources));
    }

    public List<Instruction> getInstructions() throws WrongSyntaxException {
        return compilerInstance.matcher.getInstructions();
    }

}
