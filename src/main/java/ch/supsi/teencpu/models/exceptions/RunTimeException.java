package ch.supsi.teencpu.models.exceptions;

public class RunTimeException extends ApplicationException {

    public RunTimeException(String message, int lineNr) {
        super(message, lineNr);
    }

}
