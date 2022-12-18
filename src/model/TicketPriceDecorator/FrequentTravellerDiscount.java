package model.TicketPriceDecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator{
    public FrequentTravellerDiscount(TicketPrice ticketPrice) {
        this.ticketPrice=ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice()-0.20;
    }

    @Override
    public String getPriceText() {
        return String.valueOf(ticketPrice.getPrice()-0.20);
    }
}
