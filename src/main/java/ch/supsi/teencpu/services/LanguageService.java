package ch.supsi.teencpu.services;

import ch.supsi.teencpu.path.LanguagePathManager;
import ch.supsi.teencpu.path.PathManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageService {

    private PathManager languagePathManager;

    private static LanguageService service;
    private Properties properties;

    public static LanguageService getInstance() {
        if (service == null)
            return new LanguageService();
        return service;
    }

    private LanguageService() {
        languagePathManager = new LanguagePathManager();
        initProperties();
    }

    private void initProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(languagePathManager.getPath());
            properties.load(inputStream);
            properties.putIfAbsent("current.language", "it");
            properties.store(new FileOutputStream(languagePathManager.getPath()), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentLanguage() {
        return properties.getProperty("current.language");
    }

    // language examples: italian -> it, english -> en
    public boolean changeLanguage(String language) {
        properties.setProperty("current.language", language);
        try {
            properties.store(new FileOutputStream(languagePathManager.getPath()), "");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
