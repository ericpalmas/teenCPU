package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.ApplicationFX;
import ch.supsi.teencpu.commands.Command;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class SettingControllerTest extends ApplicationTest {


    private static int SLEEP_INTERVAL = 666;

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingControllerTest.class);

    private ApplicationFX main;

    private int stepNo;

    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        LOGGER.info("STEP {}: {}", stepNo, step);
        runnable.run();
        LOGGER.info("STEP {}: End", stepNo);
    }

    @Override
    public void start(Stage stage) throws Exception {
        main = new ApplicationFX();
        main.start(stage);
    }

    @Test
    @Ignore
    public void walkThrough() {
        initialize();
        saveLanguageSetting();
        setChangeLanguageCommand();
        getRoot();
    }

    public void initialize() {
        step("init setting view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#settingBtn");
            verifyThat("#settingRoot", isVisible());
            verifyThat("#viewLbl", isVisible());
            verifyThat("#saveBtn", isVisible());
            verifyThat("#notificationLbl", isInvisible());
        });
    }

    public void saveLanguageSetting() {
        step("save lamguage setting", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#saveBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#notificationLbl", isVisible());
        });
    }

    public void setChangeLanguageCommand() {
        step("set command", () -> {
            sleep(SLEEP_INTERVAL);
            Command command = main.getMainView().getTheController().getSettingView().getTheController().getChangeLanguageCommand();
            main.getMainView().getTheController().getSettingView().getTheController().setChangeLanguageCommand(null);
            assertNull(main.getMainView().getTheController().getSettingView().getTheController().getChangeLanguageCommand());
            main.getMainView().getTheController().getSettingView().getTheController().setChangeLanguageCommand(command);
            assertNotNull(main.getMainView().getTheController().getSettingView().getTheController().getChangeLanguageCommand());
        });
    }

    public void getRoot() {
        step("get view root", () -> {
            sleep(SLEEP_INTERVAL);
            verifyThat("#settingRoot", NodeMatchers.isNotNull());
            assertEquals(lookup("#settingRoot").query(), main.getMainView().getTheController().getSettingView().getTheController().getRoot());
        });
    }
}