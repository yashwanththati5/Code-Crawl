import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchTheData{
    List<String[]> usernameData;
    public static List<String> userData;
    public void runTheLeetcodeAutomationOnUseraccount(String userName){
        new LeetCodeAutomation().startTheAutomation(userName);
    }
    public void runTheCodechefAutomationOnUseraccount(String userName){
        new CodeChefAutomation().startTheAutomation(userName);
    }
    public void runTheCodeforcesAutomationOnUseraccount(String userName)
    {
        new CodeForcesAutomation().startTheAutomation(userName);
    }
    public void runTheGeekforgeeksAutomationOnUseraccount(String userName)
    {
        new GeeksForGeeksAutomation().startTheAutomation(userName);
    }
    public void runTheAutomationOnData() throws IOException {
        userData = new ArrayList<>();
        usernameData = Data.getUsernameData();
        CsvWriter dataWriter = new CsvWriter();
        dataWriter.openTheWriter();
        dataWriter.writeTheFirstRow();
        for(int i = 1; i < usernameData.size(); i++)
        {
            String[] data = usernameData.get(i);
            for(int j = 0; j < data.length; j++)
            {
                if(j == CsvReader.getLeetcodeColumnIndex()) runTheLeetcodeAutomationOnUseraccount(data[j]);
                else if(j == CsvReader.getCodechefColumnIndex()) runTheCodechefAutomationOnUseraccount(data[j]);
                else if(j == CsvReader.getCodeforcesColumnIndex()) runTheCodeforcesAutomationOnUseraccount(data[j]);
                else if(j == CsvReader.getGeekforgeeksColumnIndex()) runTheGeekforgeeksAutomationOnUseraccount(data[j]);
                else userData.add(data[j]);
            }
            dataWriter.writeTheDataToTheFile();
            dataWriter.clearTheListData();
        }
        dataWriter.closeTheWriter();
    }
}
