package ch.supsi.teencpu.path;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ApplicationPath {

    private static Properties pathProperties;
    private static String path;

    static {
        pathProperties = new Properties();
    }

    public static void initApplicationPath() {
        try {
            pathProperties.load(ApplicationPath.class.getResourceAsStream("/path/path.properties"));
            path = System.getProperty("user.home") + "/" + getAppDirectoryName();
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createLanguagePropertyFile() {
        File file = new File(path + "/" + pathProperties.getProperty("language.file.name"));
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean createSimulationFile() {
        File file = new File(getAppDirectoryName() + "/" + pathProperties.getProperty("simulations.file.name"));
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }


    private static String getAppDirectoryName() {
        return pathProperties.getProperty("teencpu.directory");
    }

    public static Properties getPathProperties() {
        return pathProperties;
    }

    public static String getPath() {
        return path;
    }
}
