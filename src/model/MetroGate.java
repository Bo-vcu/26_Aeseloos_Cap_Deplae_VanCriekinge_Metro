package model;

import javafx.scene.control.ChoiceBox;
import model.MetroGateStates.Closed;
import model.MetroGateStates.Inactive;
import model.MetroGateStates.MetroGateState;
import model.MetroGateStates.Open;

public class MetroGate {
    private MetroGateState inactive, closed, open;
    private ChoiceBox choiceBox;

    private MetroGateState metroGateState;

    public MetroGate(){
        inactive = new Inactive(this);
        closed = new Closed(this);
        open = new Open(this);
        setMetroGateState(getInactive());

        this.choiceBox = new ChoiceBox();
    }

    public MetroGateState getMetroGateState() {
        return metroGateState;
    }

    public void setMetroGateState(MetroGateState metroGateState) {
        this.metroGateState = metroGateState;
    }

    public MetroGateState getInactive() {
        return inactive;
    }

    public MetroGateState getClosed() {
        return closed;
    }

    public MetroGateState getOpen() {
        return open;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }
}
