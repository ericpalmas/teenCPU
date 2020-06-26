package ch.supsi.teencpu.path;


public class JsonPathManager implements PathManager {


    @Override
    public String getPath() {
        return ApplicationPath.getPath() + "/" + ApplicationPath.getPathProperties().getProperty("simulations.file.name");
    }

}
