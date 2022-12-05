package controller;

import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

public class MetroTicketViewController implements Observer {
    private MetroStationView metroStationView;
    private MetroFacade metroFacade;
}
