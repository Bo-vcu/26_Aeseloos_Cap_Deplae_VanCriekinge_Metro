package model.database.loadSaveStrategies;

import model.database.MetrocardDatabase;

public interface LoadSaveStrategy {
     void load(String path, MetrocardDatabase db);
     void save(String path, MetrocardDatabase db);
}
