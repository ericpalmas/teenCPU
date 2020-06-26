package ch.supsi.teencpu.models.exceptions;

public class InvalidParametersException extends WrongSyntaxException {

    public InvalidParametersException(String message, int lineNr) {
        super(message, lineNr);
    }

    public InvalidParametersException(String message) {
        super(message);
    }
}
