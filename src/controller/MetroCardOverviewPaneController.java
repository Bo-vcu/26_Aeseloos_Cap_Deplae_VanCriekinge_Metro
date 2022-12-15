package controller;

import model.MetroEventEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroCardOverviewPane metroCardOverviewPane;
    private MetroFacade metro;

    public MetroCardOverviewPaneController(MetroFacade metro, MetroCardOverviewPane metroCardOverviewPane){
        this.metro = metro;
        this.metroCardOverviewPane = metroCardOverviewPane;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
        this.metro.addObserver(MetroEventEnum.BUY_METROCARD, this);
    }
    public void update(){
        ArrayList<Metrocard> metrocards = this.metro.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }

}
