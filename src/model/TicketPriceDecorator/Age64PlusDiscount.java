package model.TicketPriceDecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator{

    public Age64PlusDiscount(boolean is24Min, boolean is64Plus, boolean isStudent) {
        super(is24Min, is64Plus, isStudent);
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
