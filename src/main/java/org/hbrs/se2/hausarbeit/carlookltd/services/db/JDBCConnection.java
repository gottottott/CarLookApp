package org.hbrs.se2.hausarbeit.carlookltd.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    private static JDBCConnection connection = null;
    private String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/rott2s";
    private Connection conn;
    private String login = "rott2s";
    private String password ="rott2s";
    private JDBCConnection() {
        this.initConnection();
    }
    public static JDBCConnection getInstance() {
        if(connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    public void initConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.openConnection();

    }
    public void openConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", login);
            props.setProperty("password", password);
            this.conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException ex) {
            System.out.println("Klappt nicht");
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public Statement getStatement() {
        try {
            if(this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
