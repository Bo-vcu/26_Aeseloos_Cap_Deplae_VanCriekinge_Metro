package model.TicketPriceDecorator;

public abstract class TicketPrice {
    private boolean is24Min,is64Plus,isStudent;
    //private String attribute;


    public TicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent) {
        this.is24Min = is24Min;
        this.is64Plus = is64Plus;
        this.isStudent = isStudent;
    }

    public boolean isIs24Min() {
        return is24Min;
    }

    public void setIs24Min(boolean is24Min) {
        this.is24Min = is24Min;
    }

    public boolean isIs64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public double getPrice() {
        return 2.10;
    }

    public String getPriceText() {
        return "2.10";
    }


}
