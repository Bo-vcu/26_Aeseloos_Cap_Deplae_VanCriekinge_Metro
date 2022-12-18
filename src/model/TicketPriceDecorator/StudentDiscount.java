package model.TicketPriceDecorator;

public class StudentDiscount extends TicketPriceDiscountDecorator{
    public StudentDiscount(TicketPrice ticketPrice) {
        this.ticketPrice =ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice()-0.25;
    }

    @Override
    public String getPriceText() {
        return String.valueOf(ticketPrice.getPrice()-0.25);
    }


}
