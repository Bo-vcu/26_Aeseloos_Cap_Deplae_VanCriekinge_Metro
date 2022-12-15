package controller;

import model.MetroEventEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import sun.util.resources.LocaleData;
import view.MetroStationView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MetroStationViewController implements Observer {
    private MetroStationView metroStationView;
    private MetroFacade metro;

    public MetroStationViewController(MetroFacade metro, MetroStationView metroStationView) {
        this.metroStationView = metroStationView;
        this.metro = metro;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metro.getMetroCardDList();
        metroStationView.updateMetrocardIDList(ids);
    }

    public String scanMetrocard(Integer checkboxValue) {
        Metrocard metrocard = metro.getMetroCardList().get(checkboxValue - 1);
        if (metrocard.getAantalBeschikbare() != 0) {
            String[] datum = metrocard.getMaand_jaar().split("/");
            LocalDate localDate = LocalDate.of(Integer.parseInt(datum[1]) + 1,Integer.parseInt(datum[0]) + 1, 1);
            if (LocalDate.now().isBefore(localDate))
                return "Card " + checkboxValue + " is scanned";
            }
        return "Card " + checkboxValue + " is expired";
    }
}
