package ch.supsi.teencpu.models.exceptions;

public class NoParametersException extends CompilerException {

    public NoParametersException(String message, int lineNr) {
        super(message, lineNr);
    }

    public NoParametersException(String message) {
        super(message);
    }
}
