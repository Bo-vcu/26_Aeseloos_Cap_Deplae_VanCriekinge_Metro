package controller;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

import java.io.IOException;

public class ControlCenterPaneController implements Observer {
    private ControlCenterPane controlCenterPane;
    private MetroFacade metro;

    public ControlCenterPaneController(MetroFacade metro , ControlCenterPane controlCenterPane) {
        this.metro = metro;
        this.controlCenterPane = controlCenterPane;
    }

    public void openMetroStation() throws BiffException, IOException {
        metro.openMetroStation();
    }

    public void closeMetroStation() throws BiffException, WriteException, IOException {
        metro.closeMetroStation();
    }

    public void setMetroOpenOpTrue() {
        metro.setMetroOpenOpTrue();
    }

    public void setMetroOpenOpFalse() {
        metro.setMetroOpenOpFalse();
    }

    @Override
    public void update() {

    }


}
