package ch.supsi.teencpu.models.exceptions;

public class WrongSyntaxException extends CompilerException {


    public WrongSyntaxException(String message, int lineNr) {
        super(message, lineNr);
    }

    public WrongSyntaxException(String message) {
        super(message, -1);
    }

}
