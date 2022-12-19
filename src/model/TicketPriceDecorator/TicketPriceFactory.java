package model.TicketPriceDecorator;

import model.MetroFacade;
import model.Metrocard;

public class TicketPriceFactory {
    private MetroFacade metro;
    private TicketPrice price;

    public TicketPrice createTicketPrice(Integer aantal, boolean student, String ageGap) {
        TicketPrice instance = null;

        if (student) {
            switch (ageGap) {
                case "younger than 26 years":
                    //
                    break;
                case "older than 64 years":
                    //
                    break;
                case "between 26 and 64 years":
                    //
                    break;
            }
        }
        return instance;
    }

    public TicketPriceFactory() {

    }

    public double createTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent, Metrocard metrocard){
        price = new BasicTicketPrice(is24Min, is64Plus, isStudent);

        if (isStudent){
            price = new StudentDiscount(price);
        }
        if (is64Plus){
            price = new Age64PlusDiscount(price);
        }
        if (metrocard.getAantalVerbruikte() > 50){
            price = new FrequentTravellerDiscount(price);
        }
        System.out.println(price.getPrice());
        return price.getPrice();
    }
}
