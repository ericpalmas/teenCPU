package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.javafx.dialogs.NotificationTask;
import ch.supsi.teencpu.javafx.dialogs.NotificationType;
import javafx.scene.control.Label;


public class NotifySuccessCommand implements Command {

    private Label notificationLbl;
    private int duration;
    private String msg;

    public NotifySuccessCommand(Label notificationLbl, String msg) {
        this.notificationLbl = notificationLbl;
        this.duration = 4;
        this.msg = msg;
    }

    @Override
    public void execute() {
        NotificationTask notificationTask = new NotificationTask(notificationLbl, duration);
        notificationTask.setNotificationMessage(msg);
        notificationTask.setNotificationType(NotificationType.SUCCESS);
        notificationTask.show();
    }

}
