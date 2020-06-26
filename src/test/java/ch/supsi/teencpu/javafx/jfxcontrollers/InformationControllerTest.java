package ch.supsi.teencpu.javafx.jfxcontrollers;

import ch.supsi.teencpu.ApplicationFX;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class InformationControllerTest extends ApplicationTest {

    private static int SLEEP_INTERVAL = 666;

    private static final Logger LOGGER = LoggerFactory.getLogger(InformationControllerTest.class);

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
        getRoot();
    }


    public void initialize() {
        step("init info view", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#infoBtn");
            sleep(SLEEP_INTERVAL);
            verifyThat("#accordion", NodeMatchers.isNotNull());
            verifyThat("#instructionsInfoPane", NodeMatchers.isNotNull());
            verifyThat("#aboutPane", NodeMatchers.isNotNull());
            verifyThat("#accordion", isVisible());

            verifyThat("#accordion", NodeMatchers.hasChild("#instructionsInfoPane"));
            verifyThat("#accordion", NodeMatchers.hasChild("#aboutPane"));

            assertEquals(lookup("#instructionsInfoPane").query(),
                    ((Accordion) lookup("#accordion").query()).getExpandedPane());
        });
    }


    public void getRoot() {
        step("get view root", () -> {
            sleep(SLEEP_INTERVAL);
            verifyThat("#informationRoot", NodeMatchers.isNotNull());
            assertEquals(lookup("#informationRoot").query(), main.getMainView().getTheController().getInformationView().getTheController().getRoot());
        });
    }



}