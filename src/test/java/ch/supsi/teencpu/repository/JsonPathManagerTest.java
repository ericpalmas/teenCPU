package ch.supsi.teencpu.repository;

import ch.supsi.teencpu.path.ApplicationPath;
import ch.supsi.teencpu.path.JsonPathManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class JsonPathManagerTest {

    private JsonPathManager jsonPathManager;
    private Properties properties;

    @Before
    public void init() {
        ApplicationPath.initApplicationPath();
        jsonPathManager = new JsonPathManager();
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/path/path.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPath() {
        String path = ApplicationPath.getPath() + "/" + properties.getProperty("simulations.file.name");
        assertEquals(path, jsonPathManager.getPath());
    }

}