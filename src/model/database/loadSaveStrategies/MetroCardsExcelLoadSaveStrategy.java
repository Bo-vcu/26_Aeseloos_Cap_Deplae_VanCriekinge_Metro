package model.database.loadSaveStrategies;

import com.sun.rowset.internal.Row;
import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Metrocard;
import model.database.MetrocardDatabase;
import model.database.utilities.ExcelLoadSaveTemplate;

import java.io.*;
import java.util.ArrayList;

public class MetroCardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public void load(String path, MetrocardDatabase db) throws IOException, BiffException {
        File file = new File(path);
        Workbook workbook = Workbook.getWorkbook(file);
        try {
            Sheet sheet = workbook.getSheet(0);
            int aantalRows = sheet.getRows();
            int aantalColumns = sheet.getColumns();
            for (int i = 0; i < aantalColumns; i++) {
                for (int j = 0; j < aantalRows; j++) {
                    System.out.println(sheet.getCell(i,j).getContents());
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }


    }

    @Override
    public void save(String path, MetrocardDatabase db) throws IOException, WriteException, BiffException {

    }
}
