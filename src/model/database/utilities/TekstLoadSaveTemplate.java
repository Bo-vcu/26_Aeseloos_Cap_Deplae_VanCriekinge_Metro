package model.database.utilities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class TekstLoadSaveTemplate <K,V> {


        public final Map<K,V> load(File file) throws IOException {
            Map<K,V> returnMap = new HashMap<K,V>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                while (line != null && !line.trim().equals("")) {
                    String[] tokens = line.split(";");
                    V element = maakObject(tokens);
                    K key = getKey(tokens);
                    returnMap.put(key,element);
                    line = reader.readLine();
                }
            }
            return returnMap;
        }

    protected V maakObject(String[] tokens) {
        return null;
    }

    protected K getKey(String[] tokens) {
        return null;
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
