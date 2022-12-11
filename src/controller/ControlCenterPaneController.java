package controller;

import jxl.read.biff.BiffException;
import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

import java.io.IOException;

public class ControlCenterPaneController implements Observer {
    private ControlCenterPane controlCenterPane;
    private MetroFacade metroFacade = new MetroFacade();

    public ControlCenterPaneController() {

    }

    public void openMetroStation() throws BiffException, IOException {
        metroFacade.openMetroStation();
    }
}
