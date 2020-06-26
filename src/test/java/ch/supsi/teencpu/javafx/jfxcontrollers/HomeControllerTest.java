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
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class HomeControllerTest extends ApplicationTest {

    private static int SLEEP_INTERVAL = 666;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeControllerTest.class);

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
        startNewSimulation();
        setGoToSimulationCommand();
        getRoot();
    }


    public void initialize() {
        step("init home view", () -> {
            verifyThat("#logoImageView", isVisible());
            verifyThat("#startNewSimBtn", isVisible());
        });
    }


    public void startNewSimulation() {
        step("click start simulation", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#startNewSimBtn");
            verifyThat("#simulationRoot", isVisible());
        });
    }


    public void setGoToSimulationCommand() {
        step("set command", () -> {
            sleep(SLEEP_INTERVAL);
            Command command = main.getMainView().getTheController().getHomeView().getTheController().getGoToSimulationCommand();
            main.getMainView().getTheController().getHomeView().getTheController().setGoToSimulationCommand(null);
            assertNull(main.getMainView().getTheController().getHomeView().getTheController().getGoToSimulationCommand());
            main.getMainView().getTheController().getHomeView().getTheController().setGoToSimulationCommand(command);
            assertNotNull(main.getMainView().getTheController().getHomeView().getTheController().getGoToSimulationCommand());
        });
    }


    public void getRoot() {
        step("get view root", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#homeBtn");
            verifyThat("#homeRoot", NodeMatchers.isNotNull());
            assertEquals(lookup("#homeRoot").query(), main.getMainView().getTheController().getHomeView().getTheController().getRoot());
        });
    }
}