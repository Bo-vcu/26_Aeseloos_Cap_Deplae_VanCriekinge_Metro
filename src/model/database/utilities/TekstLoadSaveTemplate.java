package model.database.utilities;

import model.Metrocard;
import model.database.MetrocardDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TekstLoadSaveTemplate {

    public void load(String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //Type
                String metrocard = scanner.nextLine();
                String[] values = metrocard.split(";");
                //ID
                int int1 = Integer.parseInt(values[0]);
                //maand_jaar
                String string = values[1];
                //aantal beschikbare ritten
                int int2 = Integer.parseInt(values[2]);
                //aantal verbruikte ritten
                int int3 = Integer.parseInt(values[3]);




            }
            scanner.close();

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

            writer.close();

        } catch (IOException e) {
            System.out.println(">>> File error!!!");
            e.printStackTrace();
        }
    }
}
