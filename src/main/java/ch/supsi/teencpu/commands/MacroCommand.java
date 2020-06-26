package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.models.exceptions.ApplicationException;

import java.util.List;

public interface MacroCommand extends Command {

    void setCommands(List<? extends Command> commands);

    void executeNext() throws ApplicationException;

    void executePrevious() throws ApplicationException;

}
