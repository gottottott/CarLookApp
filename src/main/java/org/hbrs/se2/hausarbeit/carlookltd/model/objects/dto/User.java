package org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto;

public class User {
    private String vorname;
    private String nachname;
    private String mail;
    private String passwort;
    private boolean istVertriebler;
    public User() {
    }
    public User(String vorname, String nachname, String mail, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this. mail = mail;
        this.passwort = passwort;
        istVertriebler = false;
    }
    // Getter und Setter
    public String getPasswort() {
        return passwort;
    }
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
    public boolean getIstVertriebler() {
        return istVertriebler;
    }
    public void setIstVertriebler(boolean istVertriebler) {
        this.istVertriebler = istVertriebler;
    }


}
