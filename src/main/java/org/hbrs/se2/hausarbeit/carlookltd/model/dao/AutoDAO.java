package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

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
    public List<Auto> getAutos() {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(
                "SELECT *" +
                    "FROM carlook.auto ");
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

    public boolean addAuto(Auto auto) {
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        String sql = ("insert into carlook.auto values (default, ?, ?, ?, ?);");
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, auto.getMarke());
            statement.setInt(2, auto.getBaujahr());
            statement.setString(3, auto.getBeschreibung());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public void deleteCarByID(int id) {
        Statement statement = this.getStatement();
        try {
            statement.execute("DELETE FROM carlook.auto WHERE carlook.auto.auto_id = \'" + id + "\';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } {

        }
    }
}
