package controller;

import model.MetroFacade;
import model.Observer;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import view.panels.MetroStationSetupPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MetroStationSetupPaneController implements Observer {
    private MetroStationSetupPane metroStationSetupPane;
    private MetroFacade metroFacade;

    public MetroStationSetupPaneController() {

    }

    public void save(LoadSaveStrategy loadSaveStrategy) {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        properties.setProperty("database", loadSaveStrategy.getClass().getSimpleName());

    }
}
