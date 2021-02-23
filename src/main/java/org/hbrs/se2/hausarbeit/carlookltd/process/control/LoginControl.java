package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.UserDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;
import org.hbrs.se2.hausarbeit.carlookltd.views.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void checkAuthenthication(String mail, String passwort) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet set = null;

        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT *" +
                    "FROM carlook.user" +
                    " WHERE carlook.user.user_mail = \'" + mail + "\'" +
                    "AND carlook.user.user_passwort = \'" + passwort + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Entwickler informieren! ");
        }
        User user = null;
        try {
            if (set.next()) {
                user = new User();
                user.setId(set.getInt(1));
                user.setVorname(set.getString(2));
                user.setNachname(set.getString(3));
                user.setMail(set.getString(4));
                user.setIstVertriebler(set.getBoolean(6));
            } else {
                throw new NoSuchUserOrPassword();
            }
        }catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute(Roles.CURRENT_USER, user);
        UI.getCurrent().navigate(EingestellteAutosView.class);
    }
    public static void logOutUser() {
        UI.getCurrent().getPage().setLocation("/login");
        UI.getCurrent().getSession().close();
    }
    public static void addUser(User user) {
        UserDAO.getInstance().addUser(user);

    }
}
