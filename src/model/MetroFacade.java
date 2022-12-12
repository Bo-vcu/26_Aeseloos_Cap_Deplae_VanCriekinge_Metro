package model;

import jxl.read.biff.BiffException;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroFacade implements Subject {
    private Map<MetroEventEnum, List<Observer>> observers
            = new HashMap<>();
    LoadSaveStrategyFactory loadSaveStrategyFactory = new LoadSaveStrategyFactory();
    MetrocardDatabase db = new MetrocardDatabase();
    public MetroFacade(){
        for (MetroEventEnum e: MetroEventEnum.values()){
            observers.put(e, new ArrayList<Observer>());
        }
    }
    public void openMetroStation() throws BiffException, IOException {
        LoadSaveStrategy loadSaveStrategy = loadSaveStrategyFactory.createLoadSaveStrategy();
        db.setLoadSaveStrategy(loadSaveStrategy);
        db.load();
        notifyObservers(MetroEventEnum.OPEN_METROSTATION);
    }
    public ArrayList<Metrocard> getMetroCardList(){
        return db.getMetrocardList();
    }
    public ArrayList<Integer> getMetroCardDList(){
        return null;
    }

    @Override
    public void addObserver(MetroEventEnum event, Observer o) {
        observers.get(event).add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(MetroEventEnum event) {
        for (Observer obs: observers.get(event)){
            obs.update();
        }
    }
}
