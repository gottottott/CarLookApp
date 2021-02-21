package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public boolean addUser(User user) {
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
        return true;
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
