package ch.supsi.teencpu.path;

public class LanguagePathManager implements PathManager {

    @Override
    public String getPath() {
        return ApplicationPath.getPath() + "/" + ApplicationPath.getPathProperties().getProperty("language.file.name");
    }


}
