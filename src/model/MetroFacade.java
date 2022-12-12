package model;

import jxl.read.biff.BiffException;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MetroFacade {
    LoadSaveStrategyFactory loadSaveStrategyFactory = new LoadSaveStrategyFactory();
    MetrocardDatabase db = new MetrocardDatabase();
    public void openMetroStation() throws BiffException, IOException {
        LoadSaveStrategy loadSaveStrategy = loadSaveStrategyFactory.createLoadSaveStrategy();
        db.setLoadSaveStrategy(loadSaveStrategy);
        db.load();
    }
    public ArrayList<Metrocard> getMetroCardList(){
        return db.getMetrocardList();
    }
    public ArrayList<Integer> getMetroCardDList(){
        return null;
    }
}
