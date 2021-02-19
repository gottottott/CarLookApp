package org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto;

public class Auto implements java.io.Serializable {
    private int id;
    private String marke;
    private int baujahr;
    private String beschreibung;
    private int vertrieblerId;



    public Auto(int id, String marke, int baujahr, String beschreibung, int vertrieblerId) {
        this.id = id;
        this.marke = marke;
        this.baujahr = baujahr;
        this.beschreibung = beschreibung;
        this.vertrieblerId = vertrieblerId;
    }
    // Getter und Setter
    public int getId() {
        return id;
    }
    public String getMarke() {
        return marke;
    }
    public void setMarke(String marke) {
        this.marke = marke;
    }
    public int getBaujahr() {
        return baujahr;
    }
    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public int getVertrieblerId() {
        return vertrieblerId;
    }
    public void setVertrieblerId(int vertrieblerId) {
        this.vertrieblerId = vertrieblerId;
    }




}
