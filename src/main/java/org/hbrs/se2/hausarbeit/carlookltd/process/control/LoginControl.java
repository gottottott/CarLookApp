package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.UserDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;

public class LoginControl {
    private static LoginControl loginControl;
    private LoginControl() {
    }
    public static LoginControl getInstance() {
        if(loginControl == null) {
            loginControl = new LoginControl();
        }
        return loginControl;
    }
    // User einloggen
    public static void checkAuthenthication(String mail, String passwort) throws NoSuchUserOrPassword, DatabaseException {
        UserDAO.getInstance().checkAuthentication(mail, passwort);
        }
    public static void logOutUser() {
        UI.getCurrent().getPage().setLocation("/Login");
        UI.getCurrent().getSession().close();
    }
    // User registrieren
    public static void addUser(User user) throws NoSuchUserOrPassword {

            UserDAO.getInstance().addUser(user);
    }
    // Methode die Berechtigungen für den Zugang auf Views für Vertriebler prüft
    public static boolean checkIsVertriebler() {
        boolean check = true;
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        if(user == null) {
            check = false;
            UI.getCurrent().getPage().setLocation("/Login");
        }
        else if(!user.getIstVertriebler()) {
            check = false;
            UI.getCurrent().getPage().setLocation("/AutoSuche");
        }
        return check;
    }
    // Methode die Berechtigungen für den Zugang auf Views für Endkunde prüft
    public static boolean checkIsEndkunde() {
        boolean check = true;
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        if(user == null) {
            check = false;
            UI.getCurrent().getPage().setLocation("/Login");
        }
        else if(user.getIstVertriebler()) {
            check = false;
            UI.getCurrent().getPage().setLocation("/EingestellteAutos");
        }
        return check;
    }
}
