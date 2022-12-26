package model;

import java.util.ArrayList;

public class Metrostation {
    private MetroFacade metroFacade;

    private ArrayList<MetroGate> metroGates;
    public Metrostation(MetroFacade metroFacade) {
        this.metroFacade=metroFacade;
        this.metroGates=new ArrayList<>();
    }

    public String scanMetroGate(int gateId) {
        String message = metroFacade.getMetroGates().get(gateId-1).getMetroGateState().scanMetroCard();
        metroFacade.getMetroGates().get(gateId-1).setMetroGateState(metroFacade.getMetroGates().get(gateId-1).getOpen());
        return message;
    }

    public String walkTroughGate(int gateId) {
        String message = metroFacade.getMetroGates().get(gateId-1).getMetroGateState().walkTroughGate();
        metroFacade.getMetroGates().get(gateId-1).setMetroGateState(metroFacade.getMetroGates().get(gateId-1).getClosed());
        return message;
    }
}
