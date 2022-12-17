package model;

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
        //String[] parts = maand_jaar.split("#");
        //return parts[0]+"/"+parts[1];
        return maand_jaar;
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
}
