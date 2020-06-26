package ch.supsi.teencpu.models.exceptions;

public class EmptyAppStackException extends ApplicationException {

    public EmptyAppStackException(String message, int lineNr) {
        super(message, lineNr);
    }

    public EmptyAppStackException(String message) {
        super(message, -1);
    }
}
