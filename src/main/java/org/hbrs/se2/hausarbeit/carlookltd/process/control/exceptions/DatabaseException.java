package org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions;

public class DatabaseException extends Exception {
    private String reason = null;
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public DatabaseException(String reason) {
        this.reason = reason;
    }
}
