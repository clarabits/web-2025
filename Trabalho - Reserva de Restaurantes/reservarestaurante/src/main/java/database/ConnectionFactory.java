package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/devweb";
    private static final String USER = "postgres";
    private static final String PASS = "3653";

    //carrega o driver do postgres uma vez só 
    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}

