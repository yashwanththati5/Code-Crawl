package DataBase.src;

import Infrastructure.MessageHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfiguration {

    private final String connectionUsername;

    private final String connectionPassword;

    private final String connectionPort;

    private final String dataBaseName;

    private Connection mySqlConnection;

    protected DataBaseConfiguration(String username , String password, String port , String databaseName) {
        connectionUsername = username;
        connectionPassword = password;
        connectionPort = port;
        this.dataBaseName = databaseName;
        init();
    }

    private void init() {
        try {
            setupDataBase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupDataBase() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        setupConnection();
    }

    private void setupConnection() {
        try {
            mySqlConnection = DriverManager.getConnection(getConnectionString()
                    , connectionUsername , connectionPassword);
        }
        catch (SQLException e) {
            MessageHelper.showMessage(DataBaseConfiguration.class , "DataBaseConfiguration.ConnectionFailed");
        }
    }

    private String getConnectionString()
    {
        return "jdbc:mysql://localhost:" + connectionPort + "/" + dataBaseName;
    }
    public Connection getMySqlConnection() {
        return mySqlConnection;
    }
}
