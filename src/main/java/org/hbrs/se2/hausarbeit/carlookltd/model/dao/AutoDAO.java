package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoDAO {
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
        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch(DatabaseException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(
                "SELECT *" +
                    "FROM carlook.auto "); //+
                  //  "WHERE carlook.auto_marke = \'Mercedes\'");
            //     /'" + id + "/' ");

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
}
