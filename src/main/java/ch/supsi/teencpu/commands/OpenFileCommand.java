package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.jfxcontrollers.SimulationController;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileCommand implements Command {

    private SimulationController controller;


    public OpenFileCommand(SimulationController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(controller.getRoot().getScene().getWindow());
        if (file != null) {
            try {
                controller.getCodeArea().replaceText(readFile(file));
            } catch (IOException e) {
                new NotifyFailureCommand(controller.getNotificationLbl(), e.getMessage());
            }
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new FileReader(file));
        String text;
        while ((text = bufferedReader.readLine()) != null) {
            stringBuffer.append(text).append("\n");
        }
        return stringBuffer.toString().toUpperCase();
    }
}
