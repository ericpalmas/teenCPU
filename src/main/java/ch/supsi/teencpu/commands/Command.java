package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.models.exceptions.ApplicationException;

public interface Command {

    void execute() throws ApplicationException;

}
