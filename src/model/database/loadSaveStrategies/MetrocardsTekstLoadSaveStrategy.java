package model.database.loadSaveStrategies;

import model.Metrocard;
import model.database.MetrocardDatabase;
import model.database.utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MetrocardsTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{


    public void load(String path, MetrocardDatabase db) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //Type
                String metrocard = scanner.nextLine();
                String[] values = metrocard.split(";");
                //ID
                int id = Integer.parseInt(values[0]);
                //maand_jaar
                String maand_jaar = values[1];
                //aantal beschikbare ritten
                int aantalBeschikbare = Integer.parseInt(values[2]);
                //aantal verbruikte ritten
                int aantalVerbruikte = Integer.parseInt(values[3]);

                db.metrocards.put(id, new Metrocard(id, maand_jaar, aantalBeschikbare, aantalVerbruikte));


            }
            scanner.close();
            System.out.println("Successfully loaded (" + db.metrocards.size() + ") cards from file.");
        } catch (FileNotFoundException fnfe ){
            System.out.println("File doesn't exist yet.");
        } catch (Exception e) {
            System.out.println("Error found while reading file!!");
        }
    }

    public void save(String path, MetrocardDatabase db) {
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