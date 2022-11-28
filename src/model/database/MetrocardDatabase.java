package model.database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Metrocard;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MetrocardDatabase {
    //private int metroCardIid=1;
    public TreeMap<Integer, Metrocard> metrocards;
    private LoadSaveStrategy loadSaveStrategy;

    public MetrocardDatabase() {
        metrocards = new TreeMap<>();
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void load(String path) throws BiffException, IOException {
       loadSaveStrategy.load(path, this);
    }

    public void save(String path) throws WriteException, IOException, BiffException {
        loadSaveStrategy.save(path, this);
    }
    public ArrayList<Metrocard> getMetrocardList(){
        return new ArrayList<Metrocard>(metrocards.values());
    }

  public static void main(String[] args) throws BiffException, IOException, WriteException {
        MetrocardDatabase db = new MetrocardDatabase();
        db.setLoadSaveStrategy(new MetroCardsExcelLoadSaveStrategy());
        db.load("src/bestanden/metrocards.txt");
        //db.save("src/bestanden/metrocards.txt");

        System.out.println(db.getMetrocardList());
    }
}
