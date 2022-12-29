package controller;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroEventEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import view.MetroTicketView;

import java.io.IOException;
import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroTicketView metroTicketView;
    private MetroFacade metro;
    ArrayList<String> metroDiscountList;

    public MetroTicketViewController(MetroFacade metro, MetroTicketView metroTicketView){
        this.metro = metro;
        this.metroTicketView = metroTicketView;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
        this.metro.addObserver(MetroEventEnum.BUY_METROCARD, this);
//        this.metro.addObserver(TicketPriceDiscountEnum.AGE64PLUSDISCOUNT, this);
//        this.metro.addObserver(TicketPriceDiscountEnum.CHRISTMASLEAVEDISCOUNT, this);
//        this.metro.addObserver(TicketPriceDiscountEnum.STUDENTDISCOUNT, this);
//        this.metro.addObserver(TicketPriceDiscountEnum.FREQUENTTRAVELERDISCOUNT, this);
    }

    public void addMetroCard() throws BiffException, IOException, WriteException {
        metro.buyNewMetroCard();
    }

    public ArrayList<String> getMetroTicketsDiscountList(){
        return metroDiscountList;
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metro.getMetroCardDList();
        metroTicketView.updateMetrocardIDList(ids);
    }

    public double calculatePrice() throws InstantiationException, IllegalAccessException {
        double price = metro.getPrice();
        this.metroDiscountList = metro.getMetroTicketsDiscountList();
        return price;
    }

    public void addMetrotickets(int id, int amount) {
        metro.buyMetroCardTickets(id, amount);
    }
}
