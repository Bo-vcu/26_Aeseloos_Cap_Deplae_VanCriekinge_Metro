package model.MetroGateStates;

import model.MetroGate;

public class Open implements MetroGateState {

    public Open(MetroGate metroGate) {
        this.metroGate = metroGate;
    }

    private MetroGate metroGate;



    @Override
    public String walkTroughGate() {
        metroGate.setMetroGateState(metroGate.getClosed());
        return "you walked through the\ngate, it closed behind you";
    }

    @Override
    public String scanMetroCard() {
        return "gate is already open";
    }
}
