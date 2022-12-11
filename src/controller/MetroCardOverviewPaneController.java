package controller;

import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroCardOverviewPane metroCardOverviewPane;
    private MetroFacade metroFacade = new MetroFacade();

    public void update(){
        ArrayList<Metrocard> metrocards = metroFacade.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }

}
