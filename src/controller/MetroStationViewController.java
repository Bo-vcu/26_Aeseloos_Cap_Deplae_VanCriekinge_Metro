package controller;

import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

public class MetroStationViewController implements Observer {
    private MetroStationView metroStationView;
    private MetroFacade metroFacade;
}
