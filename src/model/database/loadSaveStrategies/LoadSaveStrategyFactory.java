package model.database.loadSaveStrategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadSaveStrategyFactory {

    public LoadSaveStrategy createLoadSaveStrategy() {
        Properties properties = new Properties();
        InputStream is = null;
        LoadSaveStrategy loadSaveStrategy = null;
        try {
            is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);

            Class dbClass = Class.forName("model.database.loadSaveStrategies."+properties.getProperty("database"));
            Object dbObject = dbClass.newInstance();
            loadSaveStrategy = (LoadSaveStrategy) dbObject;
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return loadSaveStrategy;
    }
}
