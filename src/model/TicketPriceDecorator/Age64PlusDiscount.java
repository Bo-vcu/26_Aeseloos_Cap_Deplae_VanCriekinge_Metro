package model.TicketPriceDecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator{

    public Age64PlusDiscount(TicketPrice ticketPrice) {
       this.ticketPrice = ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice()-0.15;
    }

    @Override
    public String getPriceText() {
        return String.valueOf(ticketPrice.getPrice()-0.15);
    }

//    public boolean isIs24Min(){
//        return super.isIs24Min();
//    }
//    public boolean isIs64Plus(){
//        return super.isIs64Plus();
//    }
//    public boolean isIsStudent(){
//        return super.isStudent();
//    }
}
