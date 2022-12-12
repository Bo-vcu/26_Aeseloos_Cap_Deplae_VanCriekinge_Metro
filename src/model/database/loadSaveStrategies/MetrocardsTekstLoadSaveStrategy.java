package model.database.loadSaveStrategies;

import model.Metrocard;
import model.database.MetrocardDatabase;
import model.database.utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MetrocardsTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{
    String path = "src/bestanden/metrocards.txt";

  public void load(MetrocardDatabase db) throws IOException {
        File file = new File(path);
        Map added = super.load(file);
        db.metrocards.putAll(added);

        System.out.println("Successfully loaded (" + added.size() + ") cards from file.");

    }

    protected Metrocard maakObject(String[] tokens) {
            Metrocard metrocard = new Metrocard(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]));
            return metrocard;
        }

    protected String getKey(String[] tokens){
        return tokens[0];
    }

    public void save(MetrocardDatabase db) {
        //Producten opslaan in een bestand
        try {
            FileWriter writer = new FileWriter(path);
            for (Metrocard metrocard : db.getMetrocardList()) {
                writer.write(metrocard.toString() + "\n");
            }
            writer.close();
            System.out.println("Successfully wrote (" + db.getMetrocardList().size() + ") products to file.");
        } catch (IOException e) {
            System.out.println(">>> File error!!!");
            e.printStackTrace();
        }
    }

}