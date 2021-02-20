package org.hbrs.se2.hausarbeit.carlookltd.model.dao;

import org.apache.juli.logging.Log;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.services.db.JDBCConnection;
import org.jboss.logging.Logger;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;

public class AbstractDAO {
    protected Statement getStatement() {
        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        return statement;
    }
    protected PreparedStatement getPreparedStatement( String sql) {
        PreparedStatement statement = null;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
