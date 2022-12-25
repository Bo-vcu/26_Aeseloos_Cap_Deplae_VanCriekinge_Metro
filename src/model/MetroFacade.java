package model;

import javafx.application.Platform;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import model.TicketPriceDecorator.TicketPriceFactory;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroFacade implements Subject {

    private boolean metroOpen = false;

    ArrayList<String> metroDiscountList = new ArrayList<>();

    private Map<MetroEventEnum, List<Observer>> observers = new HashMap<>();

    private LoadSaveStrategy loadSaveStrategy;

    private ArrayList<MetroGate> metroGates = new ArrayList<>();

    private Metrostation metrostation;
    private int gates=3;


    LoadSaveStrategyFactory loadSaveStrategyFactory = new LoadSaveStrategyFactory();
    TicketPriceFactory ticketPriceFactory = new TicketPriceFactory();
    MetrocardDatabase db = new MetrocardDatabase();


    public MetroFacade(){
        for (MetroEventEnum e: MetroEventEnum.values()){
            observers.put(e, new ArrayList<Observer>());
        }
        for (int i=0;i<gates;i++){
            MetroGate metroGate = new MetroGate();
            metroGate.setMetroGateState(metroGate.getClosed());
            metroGates.add(metroGate);
        }

        this.metrostation = new Metrostation(this);
    }

    public void setMetroOpenOpTrue() {
        this.metroOpen = true;
    }

    public void setMetroOpenOpFalse() {
        this.metroOpen = false;
    }

    public boolean getMetroOpenStatus(){
        return metroOpen;
    }

    public void openMetroStation() throws BiffException, IOException {
        LoadSaveStrategy loadSaveStrategy = loadSaveStrategyFactory.createLoadSaveStrategy();
        db.setLoadSaveStrategy(loadSaveStrategy);
        db.load();
        notifyObservers(MetroEventEnum.OPEN_METROSTATION);
    }

    public void closeMetroStation() throws BiffException, WriteException, IOException {
        db.save();
        this.loadSaveStrategy = null;
        db.setLoadSaveStrategy(null);
        notifyObservers(MetroEventEnum.CLOSE_METROSTATION);
        Platform.exit();
    }

    public ArrayList<Metrocard> getMetroCardList(){
        return db.getMetrocardList();
    }

    public ArrayList<Integer> getMetroCardDList(){
        return db.getMetroCardIDList();
    }

    public int getLastID(){
        return db.getLastID();
    }

    public void buyNewMetroCard() throws BiffException, IOException, WriteException {
        String maand = String.valueOf(LocalDate.now().getMonthValue());
        String jaar = String.valueOf(LocalDate.now().getYear());

        Metrocard metrocard = new Metrocard(getLastID(), maand +"#"+jaar,2,0);
        db.addMetrocard(metrocard);
        notifyObservers(MetroEventEnum.BUY_METROCARD);

    }

    public double getPrice(boolean is24Min, boolean is64Plus, boolean isStudent, int id){
        if (db.getMetroCardByID(id).getAantalVerbruikte() > 50){
            metroDiscountList.add(" -€0,20 FREQUENTTRAVELERDISCOUNT ");
            //notifyObservers(TicketPriceDiscountEnum.FREQUENTTRAVELERDISCOUNT);
        }
        if (is64Plus){
            metroDiscountList.add(" -€0.15 AGE64PLUSDISCOUNT ");
            //notifyObservers(TicketPriceDiscountEnum.AGE64PLUSDISCOUNT);
        }
        if (isStudent){
            metroDiscountList.add(" -€0.25 STUDENTDISCOUNT ");
            //notifyObservers(TicketPriceDiscountEnum.STUDENTDISCOUNT);
        }
        return ticketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, db.getMetroCardByID(id));
    }

    public void buyMetroCardTickets(Metrocard metrocard){

    }

    public ArrayList<String> getMetroTicketsDiscountList(){
        return metroDiscountList;
    }

    public ArrayList<MetroGate> getMetroGates() {
        return metroGates;
    }

    public String scanMetroGate(int metroCardID, int gateId){
        db.getMetroCardByID(metroCardID);
        return metrostation.scanMetroGate(gateId);
    }

    @Override
    public void addObserver(MetroEventEnum event, Observer o) {
        observers.get(event).add(o);
    }


    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }



    @Override
    public void notifyObservers(MetroEventEnum event) {
        for (Observer obs: observers.get(event)){
            obs.update();
        }
    }



}
