package controller;

import model.MetroEventEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import view.MetroStationView;
import view.MetroTicketView;

import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroTicketView metroTicketView;
    private MetroFacade metro;

    public MetroTicketViewController(MetroFacade metro, MetroTicketView metroTicketView){
        this.metro = metro;
        this.metroTicketView = metroTicketView;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metro.getMetroCardDList();
        metroTicketView.updateMetrocardIDList(ids);
    }
}
