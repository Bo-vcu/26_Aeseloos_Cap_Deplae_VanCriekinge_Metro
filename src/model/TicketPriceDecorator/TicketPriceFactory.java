package model.TicketPriceDecorator;

public class TicketPriceFactory {
    public TicketPrice createTicketPrice(Integer aantal, boolean student, String ageGap){
        TicketPrice instance = null;

        if (student) {
            switch (ageGap) {
                case "younger than 26 years":
                    //
                    break;
                case "older than 64 years":
                    //
                    break;
                case "between 26 and 64 years":
                    //
                    break;
            }
        }
        return instance;
    }
}
