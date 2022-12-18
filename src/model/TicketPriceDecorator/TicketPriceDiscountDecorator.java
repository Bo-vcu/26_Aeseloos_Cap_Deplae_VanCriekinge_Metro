package model.TicketPriceDecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice {
    public TicketPrice ticketPrice;


    public abstract double getPrice();

    public abstract String getPriceText();
}
