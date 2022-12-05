package model.database.loadSaveStrategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadSaveStrategyFactory {

    public LoadSaveStrategy createLoadSaveStrategy() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/settings.properties");

        properties.load(is);
    }
}
