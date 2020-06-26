package ch.supsi.teencpu.models.exceptions;

public class CompilerException extends ApplicationException {

    public CompilerException(String message, int lineNr) {
        super(message, lineNr);
    }


    public CompilerException(String message) {
        super(message, -1);
    }


}
