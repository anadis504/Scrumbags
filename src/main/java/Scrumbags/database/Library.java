package Scrumbags.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Library {
    private String databaseAddress;
    
    public Library(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
 
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}