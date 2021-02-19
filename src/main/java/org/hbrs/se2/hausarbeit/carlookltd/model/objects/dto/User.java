package org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto;

public class User {
    private String vorname;
    private String nachname;
    private boolean istVertriebler;
    private String mail;

    // Getter und Setter
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public boolean isIstVertriebler() {
        return istVertriebler;
    }
    public void setIstVertriebler(boolean istVertriebler) {
        this.istVertriebler = istVertriebler;
    }


}
