package model.database.loadSaveStrategies;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.database.MetrocardDatabase;
import model.database.utilities.ExcelLoadSaveTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MetroCardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public void load(String path, MetrocardDatabase db) throws IOException, BiffException {
        File inputWorkbook = new File(path);
        Workbook workbook = Workbook.getWorkbook(inputWorkbook);
    }

    @Override
    public void save(String path, MetrocardDatabase db) throws IOException, WriteException, BiffException {

    }
}
