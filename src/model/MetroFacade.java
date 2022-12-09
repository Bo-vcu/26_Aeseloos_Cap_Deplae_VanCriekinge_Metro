package model;

import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MetroFacade {
    LoadSaveStrategyFactory loadSaveStrategyFactory = new LoadSaveStrategyFactory();
    public void openMetroStation(){
        loadSaveStrategyFactory.createLoadSaveStrategy();

    }
    public ArrayList<Metrocard> getMetroCardList(){
        return null;
    }
    public ArrayList<Integer> getMetroCardDList(){
        return null;
    }
}
