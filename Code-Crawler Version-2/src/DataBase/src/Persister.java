package DataBase.src;

import AutomationCore.src.automation_helper.UserData;
import DataBase.scriptfiles.CreateSchema;
import Infrastructure.MessageHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persister {

    private static boolean isSchemaCreated = false;

    private static Persister persister = null;

    private static Connection connection = null;

    private Persister()
    {
        if(!isSchemaCreated)
        {
            isSchemaCreated = checkSchemaCreated();
        }
        if(connection == null)
        {
            connection = obtainConnection();
        }
    }

    public static Persister getPersisterInstance()
    {
        if(persister == null)
        {
            persister = new Persister();
        }
        return persister;
    }

    public void insertIntoDataBase(String ...params)
    {
        if(params.length == 4){
            insertQuery(params[0] , params[1] , params[2] , params[3]);
        }
    }
    private void insertQuery(String tableName , String userId , String solvedCount , String contestRating)
    {
        String insertQuery = "INSERT INTO " + tableName + "(userid , usersolvedcount , usercontestrating) VALUES (? , ? , ?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1 , userId);
            preparedStatement.setString(2 , solvedCount);
            preparedStatement.setString(3 , contestRating);
            preparedStatement.execute();
        } catch (SQLException e) {
            String exceptionMsg = e.getMessage();
            if(checkExceptionIsDuplicateKey(exceptionMsg)) {
                   updateQuery(tableName , userId , solvedCount , contestRating);
            }
            else {
                showErrorMessage();
                e.printStackTrace();
            }
        }
    }

    private void updateQuery(String tableName, String userId, String solvedCount , String contestRating) {
        String updateQuery = "UPDATE " + tableName + " SET usersolvedcount = ? , usercontestrating = ? WHERE userid = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1 , solvedCount);
            preparedStatement.setString(2 , contestRating);
            preparedStatement.setString(3 , userId);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            showErrorMessage();
            e.printStackTrace();
        }
    }

    public UserData fetchQuery(String tableName, String userid)
    {
        String fetchQuery = "SELECT * FROM " + tableName + " where userid = ?;";
        UserData userData = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(fetchQuery))
        {
            preparedStatement.setString(1 , userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String solvedCount = resultSet.getString("usersolvedcount");
                String contestRating = resultSet.getString("usercontestrating");
                userData = new UserData(solvedCount , contestRating);
            }
        } catch (SQLException e) {
            showErrorMessage();
            e.printStackTrace();
        }
        return userData;
    }

    private boolean checkExceptionIsDuplicateKey(String exceptionMsg) {
        return exceptionMsg.contains("Duplicate entry");
    }

    private boolean checkSchemaCreated() {
       if(!CreateSchema.isSchemaCreationSuccessful)
       {
           new CreateSchema();
       }
       return CreateSchema.isSchemaCreationSuccessful;
    }

    private Connection obtainConnection() {
       Connection connection = DataBaseConnectionSetup.getConnection();
       if(connection == null)
       {
           showErrorMessage();
       }
       return connection;
    }

    private void showErrorMessage() {
        MessageHelper.showMessage(Persister.class , "Persister.QueryExecutionFailed");
    }
}
