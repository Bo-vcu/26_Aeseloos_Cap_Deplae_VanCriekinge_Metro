package model;

import model.MetroGateStates.Inactive;

import java.time.LocalTime;
import java.util.ArrayList;

public class Metrostation {
    private MetroFacade metroFacade;

    private ArrayList<MetroGate> metroGates;
    public Metrostation(MetroFacade metroFacade) {
        this.metroFacade=metroFacade;
        this.metroGates=new ArrayList<>();
    }

    public String scanMetroGate(int gateId, int cardID) {
        Metrocard metrocard = metroFacade.getMetroCardByID(cardID);
        String message = metroFacade.getMetroGates().get(gateId-1).getMetroGateState().scanMetroCard();
        if ( metroFacade.getMetroGates().get(gateId-1).getMetroGateState() !=  metroFacade.getMetroGates().get(gateId-1).getInactive()) {
            metrocard.setAantalBeschikbare(metrocard.getAantalBeschikbare()-1);
            metrocard.setAantalVerbruikte(metrocard.getAantalVerbruikte()+1);
            metroFacade.getMetroGates().get(gateId-1).setMetroGateState(metroFacade.getMetroGates().get(gateId-1).getOpen());
        }
        else if ( metroFacade.getMetroGates().get(gateId-1).getMetroGateState() ==  metroFacade.getMetroGates().get(gateId-1).getInactive()){
            metroFacade.addAlert(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() +
                    " UNAUTHORIZED PASSAGE GATE " + gateId);
        }
        metroFacade.notifyObservers(MetroEventEnum.SCAN_METROCARD);
        return message;
    }

    public String walkTroughGate(int gateId) {
        String message = metroFacade.getMetroGates().get(gateId-1).getMetroGateState().walkTroughGate();
        if ( metroFacade.getMetroGates().get(gateId-1).getMetroGateState() !=  metroFacade.getMetroGates().get(gateId-1).getInactive()) {
            metroFacade.getMetroGates().get(gateId-1).setMetroGateState(metroFacade.getMetroGates().get(gateId-1).getClosed());
        }
        else  if ( metroFacade.getMetroGates().get(gateId-1).getMetroGateState() ==  metroFacade.getMetroGates().get(gateId-1).getInactive()){
            metroFacade.addAlert(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() +
                    " UNAUTHORIZED PASSAGE GATE " + gateId);
        }
        metroFacade.notifyObservers(MetroEventEnum.SCAN_METROCARD);
        return message;
    }
}
