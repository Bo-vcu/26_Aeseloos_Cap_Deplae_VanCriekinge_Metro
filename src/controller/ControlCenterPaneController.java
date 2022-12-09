package controller;

import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private ControlCenterPane controlCenterPane;
    private MetroFacade metroFacade = new MetroFacade();

    public ControlCenterPaneController() {

    }

    public void openMetroStation(){
        metroFacade.openMetroStation();
    }
}
