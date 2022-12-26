package model.MetroGateStates;

import model.MetroGate;

public class Closed implements MetroGateState {

    private MetroGate metroGate;

    public Closed(MetroGate metroGate) {
        this.metroGate = metroGate;
    }

    @Override
    public String walkTroughGate() {
        return "the gate is closed";
    }

    @Override
    public String scanMetroCard() {
        metroGate.setMetroGateState(metroGate.getOpen());
        return "the gate opened";
    }
}
