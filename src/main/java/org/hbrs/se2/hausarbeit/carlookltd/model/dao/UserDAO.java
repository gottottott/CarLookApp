package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;
import org.hbrs.se2.hausarbeit.carlookltd.views.EingestellteAutosView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends AbstractDAO {
    private static UserDAO dao = null;
    private UserDAO() {

    }
    public static UserDAO getInstance() {
        if( dao == null ) {
            dao = new UserDAO();
        }
        return dao;
    }
    // Methode zum einloggen des Users
    public void checkAuthentication(String mail, String passwort) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT *" +
                    "FROM carlook.user" +
                    " WHERE carlook.user.user_mail = \'" + mail + "\'" +
                    "AND carlook.user.user_passwort = \'" + passwort + "\'");
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
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
                throw new NoSuchUserOrPassword("Nicht alle Felder sind ausgefüllt!");
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
    // Methode zum registrieren eines neuen Users
    public void addUser(User user) {
        String sql = ("insert into carlook.user values (default, ?, ?, ?, ?, ?);");
        PreparedStatement statement = this.getPreparedStatement(sql);

        // Zeilenweise Abbildung der Daten auf die Spalte der erzeugten Zeile
        try {
            user.setIstVertriebler(checkIsVertriebler(user.getMail()));
            statement.setString(1, user.getVorname() );
            statement.setString(2, user.getNachname());
            statement.setString(3,user.getMail());
            statement.setString(4,user.getPasswort());
            statement.setBoolean(5,user.getIstVertriebler());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean checkIsVertriebler(String param) {
        boolean isVertriebler = false;
        // Teile die Mail nach dem @-Zeichen und prüfe ob sie mit "carlook.de" endet. Dann ist es ein Vertriebler
        if ("@carlook.de".equals(param.substring(param.indexOf('@'),param.length()))) {
             isVertriebler = true;
        }
        return isVertriebler;
    }
}
