package DataBase.src;

import Infrastructure.Environment;

import java.sql.Connection;

public class DataBaseConnectionSetup {

    private static Connection connection = null;

    private DataBaseConnectionSetup(){
        init();
    }

    private void init() {
        String username = Environment.getDatabaseConnectionUsername();
        String userPassword = Environment.getDatabaseConnectionPassword();
        String dataBasePort = "3306";
        String dataBaseName = Environment.getDataBaseName();
        connection = new DataBaseConfiguration(username , userPassword , dataBasePort , dataBaseName).getMySqlConnection();
    }

    public static Connection getConnection()
    {
        if(connection == null)
        {
            new DataBaseConnectionSetup();
        }
        return connection;
    }
}
