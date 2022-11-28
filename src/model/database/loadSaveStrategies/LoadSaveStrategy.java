package model.database.loadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.database.MetrocardDatabase;

import java.io.IOException;
import java.util.ArrayList;

public interface LoadSaveStrategy {

     void load(String path, MetrocardDatabase db) throws IOException, BiffException;
     void save(String path, MetrocardDatabase db) throws IOException, WriteException, BiffException;
}
