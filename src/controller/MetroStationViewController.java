package controller;

import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

public class MetroStationViewController implements Observer {
    private MetroStationView metroStationView;
    private MetroFacade metro;

    public MetroStationViewController(MetroFacade metro, MetroStationView metroStationView) {
        this.metroStationView = metroStationView;
        this.metro = metro;
    }

    @Override
    public void update() {

    }
}
