package ch.supsi.teencpu.models.exceptions;

public class FullOutputException extends ApplicationException {
    public FullOutputException(String message, int lineNr) { super(message, lineNr); }
    public FullOutputException(String message) { super(message, -1); }

}
