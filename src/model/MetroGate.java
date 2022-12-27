package model;

import javafx.scene.control.ChoiceBox;
import model.MetroGateStates.Closed;
import model.MetroGateStates.Inactive;
import model.MetroGateStates.MetroGateState;
import model.MetroGateStates.Open;

public class MetroGate {
    private MetroGateState inactive, closed, open;
    private ChoiceBox choiceBox;
    private int aantalScannedCards;
    private int id;

    private MetroGateState metroGateState;

    public MetroGate(){
        inactive = new Inactive(this);
        closed = new Closed(this);
        open = new Open(this);
        setMetroGateState(getInactive());
        this.aantalScannedCards = 0;
        this.choiceBox = new ChoiceBox();
    }

    public void setAantalScannedCards(int aantalScannedCards) {
        this.aantalScannedCards = aantalScannedCards;
    }

    public int getAantalScannedCards() {
        return aantalScannedCards;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
