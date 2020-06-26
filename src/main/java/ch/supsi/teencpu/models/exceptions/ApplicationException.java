package ch.supsi.teencpu.models.exceptions;

public abstract class ApplicationException extends Exception {

    private int lineNr;

    public ApplicationException(String message, int lineNr) {
        super("" + lineNr + "\n" + message);
        this.lineNr = lineNr;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public int getLineNr() {
        return lineNr;
    }


}
