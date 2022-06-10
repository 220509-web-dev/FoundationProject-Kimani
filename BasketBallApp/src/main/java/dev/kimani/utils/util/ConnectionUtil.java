package dev.kimani.utils.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static ConnectionUtil instance;

    private final Properties props = new Properties();


    public static ConnectionUtil getInstance(){
        if(instance == null) {
            instance = new ConnectionUtil();
        }

        return instance;
    }


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(Exception e) {
            System.err.println("Failed to load Postgres.");
            throw new RuntimeException();
        }
    }

    private ConnectionUtil() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.err.println("Could not load DB credentials from properties file");
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() throws SQLException {

//      String dbInfo = System.getenv("DB_CONNECTION");
        try {
            Connection connection = DriverManager.getConnection(props.getProperty("db_url"),
                                                                props.getProperty("db_username"),
                                                                props.getProperty("db_password"));
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not establish a connection with database");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
}
