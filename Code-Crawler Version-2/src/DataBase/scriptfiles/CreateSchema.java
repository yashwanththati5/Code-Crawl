package DataBase.scriptfiles;

import DataBase.src.DataBaseConnectionSetup;
import Infrastructure.Environment;
import Infrastructure.MessageHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CreateSchema {

    public static boolean isSchemaCreationSuccessful = false;

    private static final String path = "src/DataBase/scriptfiles/create_schema.sql";

    public CreateSchema()
    {
        init();
    }

    private void init() {
       List<String> sqlQueries = readSchemaFile();
       sqlQueries.forEach(this::executeQuery);
       isSchemaCreationSuccessful = true;
       System.out.println("Schema creation is done on data base: " + Environment.getDataBaseName());
    }

    private List<String> readSchemaFile()
    {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            MessageHelper.showMessage(CreateSchema.class , "CreateSchema.SchemaFileNotFound");
        }
        String[] sqlQueries = content.toString().split(";");
        return Arrays.stream(sqlQueries).toList();
    }

    private void executeQuery(String sqlQuery){
        Connection connection = DataBaseConnectionSetup.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            isSchemaCreationSuccessful = false;
            MessageHelper.showMessage(CreateSchema.class , "CreateSchema.SchemaCreationFailed");
        }
    }

}
