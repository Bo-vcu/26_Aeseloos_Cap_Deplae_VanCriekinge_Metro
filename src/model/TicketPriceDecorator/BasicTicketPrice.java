package model.TicketPriceDecorator;

public class BasicTicketPrice extends TicketPrice {
    public BasicTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent) {
        this.setIs24Min(is24Min);
        this.setIs64Plus(is64Plus);
        this.setStudent(isStudent);
    }

    public double getPrice() {
        return 2.10;
    }

    public String getPriceText() {
        return "2.10";
    }
}
