package org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto;

public class Reservierung {
    int reservierungsID;
    int autoID;
    int userID;

    public Reservierung(int autoID, int userID) {
        this.autoID = autoID;
        this.userID = userID;
    }

    public Reservierung() {

    }

    public int getReservierungsID() {
        return reservierungsID;
    }
    public void setReservierungsID(int reservierungsID) {
        this.reservierungsID = reservierungsID;
    }
    public int getAutoID() {
        return autoID;
    }
    public void setAutoID(int autoID) {
        this.autoID = autoID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
