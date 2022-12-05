package model.database.loadSaveStrategies;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadSaveStrategyFactory {

    public LoadSaveStrategy createLoadSaveStrategy() {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/settings.properties");

        properties.load(is);
    }
}
