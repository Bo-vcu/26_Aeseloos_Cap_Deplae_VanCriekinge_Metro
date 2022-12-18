package model;

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


    LoadSaveStrategyFactory loadSaveStrategyFactory = new LoadSaveStrategyFactory();
    TicketPriceFactory ticketPriceFactory = new TicketPriceFactory();
    MetrocardDatabase db = new MetrocardDatabase();

    public MetroFacade(){
        for (MetroEventEnum e: MetroEventEnum.values()){
            observers.put(e, new ArrayList<Observer>());
        }
    }

    public void setMetroOpenOpTrue() {
        this.metroOpen = true;
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

    public void scanMetroGate(int metroCardID, int gateId){

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
