package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoDAO extends AbstractDAO {
    public static AutoDAO dao = null;
    private AutoDAO() {
    }
    public static AutoDAO getInstance() {
        if( dao == null ) {
            dao = new AutoDAO();
        }
        return dao;
    }
    // Autos anhand einer Marke ausgeben
    public List<Auto> getAutosByMarke(String marke) {
        String sql;
        // Leeres Suchfeld gibt alle Autos aus
        if(marke == "") {
            sql = "SELECT * FROM carlook.auto;";
        } else {
            sql = "SELECT * FROM carlook.auto WHERE carlook.auto.auto_marke = \'" + marke + "\';";
        }
        Statement statement = this.getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
            } catch (SQLException ex){
        }
        if ( rs == null) return null;
        List<Auto> autoListe = new ArrayList<Auto>();
        Auto auto = null;

        try {
        while(rs.next()) {
            auto =  new Auto();
            auto.setId(rs.getInt(1));
            auto.setMarke(rs.getString(2));
            auto.setBaujahr(rs.getInt(3));
            auto.setBeschreibung(rs.getString(4));
            auto.setVertrieblerId(rs.getInt(5));
            autoListe.add(auto);
        }
        } catch (SQLException ex) {

        }
        return autoListe;
    }
    // Autos von einem Vertriebler ausgeben
    public List<Auto> getAutosByVertriebler(int vertriebler) {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(
                    "SELECT *" +
                            "FROM carlook.auto " +
                            "WHERE carlook.auto.user_id =  \'" + vertriebler + "\';"
            );
        } catch (SQLException ex){
        }
        if ( rs == null) return null;
        List<Auto> autoListe = new ArrayList<Auto>();
        Auto auto = null;

        try {
            while(rs.next()) {
                auto =  new Auto();
                auto.setId(rs.getInt(1));
                auto.setMarke(rs.getString(2));
                auto.setBaujahr(rs.getInt(3));
                auto.setBeschreibung(rs.getString(4));
                auto.setVertrieblerId(rs.getInt(5));
                autoListe.add(auto);
            }
        } catch (SQLException ex) {

        }
        return autoListe;
    }
    // fügt ein Auto hinzu
    public boolean addAuto(Auto auto) throws DatabaseException {
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        String sql = ("insert into carlook.auto values (default, ?, ?, ?, ?);");
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            if (autoVorhanden(auto)) {
                throw new DatabaseException("Fehler Datensatz bereits vorhanden");
            } else {
                statement.setString(1, auto.getMarke());
                statement.setInt(2, auto.getBaujahr());
                statement.setString(3, auto.getBeschreibung());
                statement.setInt(4, user.getId());
                statement.executeUpdate();
            }
        }catch (SQLException ex) {
             ex.printStackTrace();
        }
        return true;
    }
   public boolean autoVorhanden(Auto auto) {
        return false;
   }
        /*
        Statement statement1 = this.getStatement();
        ResultSet rs = null;
        String i = "asdf";
        try {
            rs = statement1.executeQuery("SELECT COUNT * " +
                    "FROM carlook.user "
                 //   "WHERE carlook.auto.auto_marke = \'" + i + "\';"); //auto.getMarke() + "\' " +
            );
           System.out.println(rs.getInt("total"));
            if(rs.getInt(1) == 0) {
                return false;
            }
        } catch (SQLException ex) {

        }
        //  "AND carlook.auto.auto_baujahr = \'" + auto.getBaujahr() + "\'  +" +
        //  "AND carlook.auto.auto_beschreibung = \'" + auto.getBeschreibung() + "\'");
        return true;
    }

 */
    // Auto anhand der Id aus Datenbank entfernen
    public void deleteCarByID(int id) {
        Statement statement = this.getStatement();
        try {
            statement.execute("DELETE FROM carlook.auto WHERE carlook.auto.auto_id = \'" + id + "\';");
            statement.execute("DELETE FROM carlook.reservierung WHERE carlook.reservierung.auto_id = \'" + id + "\';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
