package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.commands.ChangeLanguageCommand;
import ch.supsi.teencpu.commands.Command;
import ch.supsi.teencpu.javafx.dialogs.NotificationTask;
import ch.supsi.teencpu.javafx.dialogs.NotificationType;
import ch.supsi.teencpu.models.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class SettingController extends AppController implements Initializable {

    private Command changeLanguageCommand;

    @FXML
    private VBox settingRoot;

    @FXML
    private ChoiceBox<Locale> languageChoiceBox;


    @FXML
    private Label viewLbl;


    @FXML
    private Button saveBtn;

    @FXML
    private Label notificationLbl;


    @FXML
    void saveLanguageSetting() {
        Locale selectedLocal = languageChoiceBox.getSelectionModel().getSelectedItem();
        ((ChangeLanguageCommand) changeLanguageCommand).setLanguageToSet(selectedLocal.toString());
        try {
            changeLanguageCommand.execute();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        NotificationTask notification = new NotificationTask(notificationLbl, 5);
        notification.setNotificationType(NotificationType.SUCCESS);
        notification.setNotificationMessage(ResourceBundle.getBundle("i18n/strings").getString("language.changed.success"));
        notification.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale currentLocale = Locale.getDefault();
        Set<Locale> supportedLocales = new HashSet<>();
        supportedLocales.add(currentLocale);
        supportedLocales.add(Locale.ITALIAN);
        supportedLocales.add(Locale.ENGLISH);
        languageChoiceBox.getItems().addAll(supportedLocales);
        languageChoiceBox.getSelectionModel().select(currentLocale);
    }

    public void setChangeLanguageCommand(Command changeLanguageCommand) {
        this.changeLanguageCommand = changeLanguageCommand;
    }

    public Command getChangeLanguageCommand() {
        return changeLanguageCommand;
    }

    @Override
    public Parent getRoot() {
        return settingRoot;
    }

}
