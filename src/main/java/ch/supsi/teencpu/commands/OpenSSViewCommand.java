package ch.supsi.teencpu.commands;


import java.net.URL;
import java.util.ResourceBundle;

public class OpenSSViewCommand implements Command {

    private URL fxmlPath;
    private ResourceBundle resourceBundle;


    public OpenSSViewCommand(URL fxmlPath, ResourceBundle resourceBundle) {
        this.fxmlPath = fxmlPath;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public void execute() {

    }
}
