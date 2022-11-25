package model.database;

import model.Metrocard;

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

    public MetrocardDatabase() {
        metrocards = new TreeMap<>();
    }

    public void load(String path) {
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

                this.metrocards.put(id, new Metrocard(id, maand_jaar, aantalBeschikbare, aantalVerbruikte));


            }
            scanner.close();
            System.out.println("Successfully loaded (" + metrocards.size() + ") cards from file.");
        } catch (FileNotFoundException fnfe ){
            System.out.println("File doesn't exist yet.");
        } catch (Exception e) {
            System.out.println("Error found while reading file!!");
        }
    }

    public void save(String path) {
        //Producten opslaan in een bestand
        try {
            FileWriter writer = new FileWriter(path);
            for (Metrocard metrocard : getMetrocardList()) {
                writer.write(metrocard.toString() + "\n");
            }
            writer.close();
            System.out.println("Successfully wrote (" + getMetrocardList().size() + ") products to file.");
        } catch (IOException e) {
            System.out.println(">>> File error!!!");
            e.printStackTrace();
        }
    }
    public ArrayList<Metrocard> getMetrocardList(){
        return new ArrayList<Metrocard>(metrocards.values());
    }

    /*public static void main(String[] args) {
        MetrocardDatabase db = new MetrocardDatabase();
        db.load("26_Aesloos_Cap_Deplae_VanCriekinge_Metro/src/bestanden/metrocards.txt");
        db.save("26_Aesloos_Cap_Deplae_VanCriekinge_Metro/src/bestanden/metrocards.txt");

        System.out.println(db.getMetrocardList());
    }*/
}
