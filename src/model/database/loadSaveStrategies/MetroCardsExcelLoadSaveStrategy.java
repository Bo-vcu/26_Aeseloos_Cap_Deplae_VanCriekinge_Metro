package model.database.loadSaveStrategies;

import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.Metrocard;
import model.database.MetrocardDatabase;
import model.database.utilities.ExcelLoadSaveTemplate;

import java.io.*;
import java.util.ArrayList;

public class MetroCardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public void load(String path, MetrocardDatabase db) {
        File file = new File(path);
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            int row = 0;
            ArrayList<ArrayList<String>> info = new ArrayList<>();
            while (row < sheet.getRows()) {
                int column = 0;
                ArrayList<String> rowinfo = new ArrayList<>();
                while (column < sheet.getColumns()) {
                    Cell cell = sheet.getCell(column, row);
                    String information = cell.getContents();
                    rowinfo.add(information);
                    column++;
                }
                info.add(rowinfo);
                row++;
            }
            for (ArrayList<String> metrocard : info) {
                db.metrocards.put(Integer.parseInt(metrocard.get(0)), new Metrocard(Integer.parseInt(metrocard.get(0)), metrocard.get(1), Integer.parseInt(metrocard.get(2)), Integer.parseInt(metrocard.get(3))));
            }
            System.out.println("Successfully loaded (" + db.metrocards.size() + ") cards from file.");
            workbook.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist yet.");
        } catch (IOException | BiffException e) {
            System.out.println("Error found while reading file!!");
        }
    }

    @Override
    public void save(String path, MetrocardDatabase db) throws IOException, WriteException {
        File file = new File(path);
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            workbook.createSheet("sheet1", 0);
            WritableSheet sheet = workbook.getSheet(0);
            for (int i = 0; i < db.metrocards.size(); i++) {
                String met = db.getMetrocardList().get(i).toString();
                for (int j = 0; j < 4; j++) {
                    Label label = new Label(j, i, met.split(";")[j]);
                    sheet.addCell(label);
                }
            }
            workbook.write();
            workbook.close();
            System.out.println("Successfully saved (" + db.metrocards.size() + ") cards from db.");
        } catch (IOException | WriteException e) {
            System.out.println(">>> File error!!!");
            e.printStackTrace();
        }
    }


}
