package org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions;

public class NoSuchUserOrPassword extends Exception {
    String toString;
    public NoSuchUserOrPassword(String error) {
        this.toString = error;
    }


}
