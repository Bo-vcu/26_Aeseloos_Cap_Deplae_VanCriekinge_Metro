package model;

import java.time.LocalDate;

public class Metrocard {
    private int id;
    private String maand_jaar;
    private int aantalBeschikbare =2;
    private int aantalVerbruikte =0;

    public Metrocard() {
    }

    public Metrocard(int id, String maand_jaar, int aantalBeschikbare, int aantalVerbruikte) {
        this.id = id;
        this.maand_jaar = maand_jaar;
        this.aantalBeschikbare = aantalBeschikbare;
        this.aantalVerbruikte = aantalVerbruikte;
    }

    //getters niet verwijderen!!!!!!!!!!!!!
    public int getId() {
        return id;
    }

    public String getMaand_jaar() {
        String[] parts = maand_jaar.split("#");
        return parts[0]+"/"+parts[1];
    }

    public int getAantalBeschikbare() {
        return aantalBeschikbare;
    }

    public int getAantalVerbruikte() {
        return aantalVerbruikte;
    }

    public void setMaand_jaar(String maand_jaar) {
        this.maand_jaar = maand_jaar;
    }

    public void setAantalBeschikbare(int aantalBeschikbare) {
        this.aantalBeschikbare = aantalBeschikbare;
    }

    public void setAantalVerbruikte(int aantalVerbruikte) {
        this.aantalVerbruikte = aantalVerbruikte;
    }
    public String toString(){
        return id+";"+maand_jaar+";"+aantalBeschikbare+";"+aantalVerbruikte;

    }

    public static boolean isExpired(Metrocard metrocard) {
        String[] datum = metrocard.getMaand_jaar().split("/");

            LocalDate localDate;

            if (Integer.parseInt(datum[0])==12){
                localDate = LocalDate.of(Integer.parseInt(datum[1]) + 2,1, 1);
            }
            else {
                localDate = LocalDate.of(Integer.parseInt(datum[1]) + 1,Integer.parseInt(datum[0]) + 1, 1);
            }

        return localDate.isBefore(LocalDate.now());
    }
}
