package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.ApplicationFX;
import javafx.stage.Stage;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class MainControllerTest extends ApplicationTest {


    private static int SLEEP_INTERVAL = 666;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainControllerTest.class);

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
      goHome();
      goToSimulation();
      goToSetting();
      goToInformation();
      getHomeView();
      getSimulationView();
      getSettingView();
      getInformationView();
      getSsView();
      getRoot();
    }


    public void initialize() {
        step("init main view", () -> {
            sleep(SLEEP_INTERVAL);
            assertNotNull(main.getMainView().getTheController().getHomeView());
            assertNotNull(main.getMainView().getTheController().getSimulationView());
            assertNotNull(main.getMainView().getTheController().getSettingView());
            assertNotNull(main.getMainView().getTheController().getInformationView());
            assertNotNull(main.getMainView().getTheController().getSsView());

            verifyThat("#topMenuHBox", isVisible());
            verifyThat("#homeBtn", isVisible());
            verifyThat("#simulationBtn", isVisible());
            verifyThat("#settingBtn", isVisible());
            verifyThat("#infoBtn", isVisible());

            verifyThat("#homeRoot", isVisible());
            assertEquals(lookup("#homeBtn").query().getStyleClass().get(0), "menu-buttons-clicked");
        });

    }


    public void goHome() {
        step("go to home view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#homeBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#homeRoot", isVisible());
            assertEquals(lookup("#homeBtn").query().getStyleClass().get(0), "menu-buttons-clicked");
        });
    }


    public void goToSimulation() {
        step("go to simulation view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#simulationBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#simulationRoot", isVisible());
            assertEquals(lookup("#simulationBtn").query().getStyleClass().get(0), "menu-buttons-clicked");
        });
    }


    public void goToSetting() {
        step("go to setting view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#settingBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#settingRoot", isVisible());
            assertEquals(lookup("#settingBtn").query().getStyleClass().get(0), "menu-buttons-clicked");
        });
    }


    public void goToInformation() {
        step("go to information view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#infoBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#informationRoot", isVisible());
            assertEquals(lookup("#infoBtn").query().getStyleClass().get(0), "menu-buttons-clicked");
        });
    }


    public void getRoot() {
        step("get view root", () -> {
            sleep(SLEEP_INTERVAL);
            verifyThat("#mainRoot", NodeMatchers.isNotNull());
            assertEquals(lookup("#mainRoot").query(), main.getMainView().getTheController().getRoot());
        });
    }


    public void getHomeView() {
        assertNotNull(main.getMainView().getTheController().getHomeView());
    }


    public void getSimulationView() {
        assertNotNull(main.getMainView().getTheController().getSimulationView());
    }


    public void getSettingView() {
        assertNotNull(main.getMainView().getTheController().getSettingView());
    }


    public void getInformationView() {
        assertNotNull(main.getMainView().getTheController().getInformationView());
    }


    public void getSsView() {
        assertNotNull(main.getMainView().getTheController().getSsView());
    }

}