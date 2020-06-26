package ch.supsi.teencpu.models.exceptions;

public class JumpExeption extends ApplicationException {

    public JumpExeption(String message, int lineNr) {
        super(message, lineNr);
    }

    public JumpExeption(String message) {
        super(message, -1);
    }
}
