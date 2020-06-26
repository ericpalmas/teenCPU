package ch.supsi.teencpu.repository;

public class JsonNotFoundException extends RepositoryException {

    public JsonNotFoundException() {
        super("Can't find the requested json file");
    }
}
