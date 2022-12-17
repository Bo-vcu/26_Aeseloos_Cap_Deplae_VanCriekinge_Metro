package model.TicketPriceDecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice {
    protected TicketPrice ticketPrice;

    public TicketPriceDiscountDecorator(boolean is24Min, boolean is64Plus, boolean isStudent) {
        super(is24Min, is64Plus, isStudent);
    }

    public abstract double getPrice();

    public abstract String getPriceText();
}
