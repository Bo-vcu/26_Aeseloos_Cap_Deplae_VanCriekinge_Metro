package model.MetroGateStates;

import model.MetroGate;

public class Inactive implements MetroGateState {

   private MetroGate metroGate;

   public Inactive(MetroGate metroGate){
       this.metroGate=metroGate;
   }
    @Override
    public String walkTroughGate() {
        return "gate inactive";
    }

    @Override
    public String scanMetroCard() {
        return "gate inactive";
    }
}
