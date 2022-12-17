package model.TicketPriceDecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator{
    public FrequentTravellerDiscount(boolean is24Min, boolean is64Plus, boolean isStudent) {
        super(is24Min, is64Plus, isStudent);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getPriceText() {
        return null;
    }
}
