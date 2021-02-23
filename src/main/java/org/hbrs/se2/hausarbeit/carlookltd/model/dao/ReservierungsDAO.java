package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Reservierung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservierungsDAO extends AbstractDAO {
    private static ReservierungsDAO reservDAO;
    private ReservierungsDAO() {
    }
    public static ReservierungsDAO getInstance() {
        if(reservDAO == null) {
            reservDAO = new ReservierungsDAO();
        }
        return reservDAO;
    }

    public void addReservierung(Reservierung reserv) {
        String sql = ("insert into carlook.reservierung values (default, ?, ?);");
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, reserv.getAutoID());
            statement.setInt(2, reserv.getUserID());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Auto> getReservierteAutosByUserID(int id) {

        Statement statement = this.getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(
                    "SELECT carlook.auto.auto_id, carlook.auto.auto_marke, carlook.auto.auto_baujahr, carlook.auto.auto_beschreibung, carlook.auto.user_id " +
                            "FROM carlook.auto " +
                            "JOIN carlook.reservierung " +
                            "ON (carlook.auto.auto_id = carlook.reservierung.auto_id) " +
                            "WHERE carlook.reservierung.user_id = \'" + id + "\';"
        );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if ( rs == null) return null;
        List<Auto> autoListe = new ArrayList<Auto>();
        Auto auto;
      //  Reservierung reserv = null;
        try {
            while(rs.next()) {
                auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setMarke(rs.getString(2));
                auto.setBaujahr(rs.getInt(3));
                auto.setBeschreibung(rs.getString(4));

           //     auto.setVertrieblerId(rs.getInt(5));
                autoListe.add(auto);
            }
        } catch (SQLException ex) {
        }
        return autoListe;

    }
    public void deleteReservierungByID(Reservierung reserv) {
        Statement statement = this.getStatement();
        try {
            statement.execute("DELETE FROM carlook.reservierung WHERE carlook.reservierung.user_id = \'" + reserv.getUserID() + "\' " +
                    "AND carlook.reservierung.auto_id = \'" + reserv.getAutoID() + "\';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
