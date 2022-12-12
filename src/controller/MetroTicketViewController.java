package controller;

import model.MetroFacade;
import model.Observer;
import view.MetroStationView;
import view.MetroTicketView;

public class MetroTicketViewController implements Observer {
    private MetroTicketView metroTicketView;
    private MetroFacade metro;

    public MetroTicketViewController(MetroFacade metro, MetroTicketView metroTicketView){
        this.metro = metro;
        this.metroTicketView = metroTicketView;
    }

    @Override
    public void update() {

    }
}
