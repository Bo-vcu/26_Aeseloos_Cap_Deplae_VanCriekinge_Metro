package model.TicketPriceDecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator{
    public ChristmasLeaveDiscount(TicketPrice ticketPrice) {
        this.ticketPrice=ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice()-0.10;
    }

    @Override
    public String getPriceText() {
        return String.valueOf(ticketPrice.getPrice()-0.10);
    }
}
