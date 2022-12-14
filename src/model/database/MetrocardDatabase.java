package model.database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Metrocard;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
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

    public void load() throws BiffException, IOException {
       loadSaveStrategy.load(this);
    }

    public void save() throws WriteException, IOException, BiffException {
        loadSaveStrategy.save(this);
    }
    public ArrayList<Metrocard> getMetrocardList(){
        return new ArrayList<Metrocard>(metrocards.values());
    }

    public ArrayList<Integer> getMetroCardIDList(){
        ArrayList<Integer> a = new ArrayList<>();
       for(Metrocard metrocard : metrocards.values()){
           a.add(Integer.parseInt(metrocard.toString().split(";")[0]));

       }
        System.out.println(a);
       return a;
    }

  /*public static void main(String[] args) throws BiffException, IOException, WriteException {
       Properties properties = new Properties();
      InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));

      properties.load(is);
      String dbType = properties.getProperty("database");

        MetrocardDatabase db = new MetrocardDatabase();


        db.setLoadSaveStrategy(new MetroCardsExcelLoadSaveStrategy());
        System.out.println("src/bestanden/metrocards."+dbType);
        db.load("src/bestanden/metrocards."+dbType);
        //db.save("src/bestanden/metrocards."+dbType);


        System.out.println(db.getMetrocardList());
    }*/
}
