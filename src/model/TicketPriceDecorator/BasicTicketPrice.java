package model.TicketPriceDecorator;

public class BasicTicketPrice extends TicketPrice {
    public BasicTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent) {
        super(is24Min, is64Plus, isStudent);
    }

    public double getPrice() {
        return 2.10;
    }

    public String getPriceText() {
        return "2.10";
    }
}
