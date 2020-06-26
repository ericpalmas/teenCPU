package ch.supsi.teencpu.repository;

import ch.supsi.teencpu.models.exceptions.ApplicationException;

public abstract class RepositoryException extends ApplicationException {

    public RepositoryException(String message) {
        super(message);
    }
}
