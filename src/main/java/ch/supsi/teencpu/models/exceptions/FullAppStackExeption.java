package ch.supsi.teencpu.models.exceptions;

public class FullAppStackExeption extends ApplicationException {
    public FullAppStackExeption(String message, int lineNr) { super(message, lineNr); }
    
    public FullAppStackExeption(String message) { super(message, 1); }

}
