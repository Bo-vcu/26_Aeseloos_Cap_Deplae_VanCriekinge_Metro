package controller;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroEventEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import view.MetroStationView;
import view.MetroTicketView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroTicketView metroTicketView;
    private MetroFacade metro;

    public MetroTicketViewController(MetroFacade metro, MetroTicketView metroTicketView){
        this.metro = metro;
        this.metroTicketView = metroTicketView;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
        this.metro.addObserver(MetroEventEnum.BUY_METROCARD, this);
    }

    public void addMetroCard() throws BiffException, IOException, WriteException {
        metro.addMetroCard();
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metro.getMetroCardDList();
        metroTicketView.updateMetrocardIDList(ids);
    }
}
