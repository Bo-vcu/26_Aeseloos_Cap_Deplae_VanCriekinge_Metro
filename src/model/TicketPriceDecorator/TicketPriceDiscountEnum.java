package model.TicketPriceDecorator;


import jxl.read.biff.BiffException;
import model.database.loadSaveStrategies.LoadSaveStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("64 plus discount",Age64PlusDiscount.class),
    CHRISTMASLEAVEDISCOUNT("christmas leave discount", ChristmasLeaveDiscount.class),
    STUDENTDISCOUNT("student discount", StudentDiscount.class),
    FREQUENTTRAVELERDISCOUNT("frequent traveller discount", FrequentTravellerDiscount.class);

    private String name;
    private Class<? extends TicketPrice> ticketPrice;

    TicketPriceDiscountEnum(String name, Class<? extends TicketPrice> ticketPrice) {
        this.name = name;
        this.ticketPrice = ticketPrice;
    }

    public Class<? extends TicketPrice> getTicketPrice() {
        return ticketPrice;
    }

    public boolean isActive() {
        Properties properties = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);

            String value = properties.getProperty(this+"_enabled");

            if (value==null){
                return false;
            }

            return Boolean.parseBoolean(value);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
