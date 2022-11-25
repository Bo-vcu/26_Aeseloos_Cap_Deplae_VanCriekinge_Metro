package model.database.loadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.database.MetrocardDatabase;
import model.database.utilities.ExcelLoadSaveTemplate;

import java.io.IOException;

public class MetroCardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public void load(String path, MetrocardDatabase db) throws IOException, BiffException {

    }

    @Override
    public void save(String path, MetrocardDatabase db) throws IOException, WriteException {

    }
}
