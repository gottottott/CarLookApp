package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;
import org.hbrs.se2.hausarbeit.carlookltd.views.eingestellteautos.EingestellteAutosView;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {
    public static void checkAuthenthication(String mail, String passwort) throws NoSuchUserOrPassword {
        ResultSet set = null;

        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT *" +
                    "FROM carlook.user" +
                    " WHERE carlook.user.user_mail = \'" + mail + "\'" +
                    "AND carlook.user.user_passwort = \'" + passwort + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = null;
        try {
            if (set.next()) {
                user = new User();
                user.setMail(set.getString(1));
                user.setVorname(set.getString(3));
                user.setNachname(set.getString(4));
                user.setIstVertriebler(set.getBoolean(5));
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
}