package AutomationCore.src.automation_code;

import AutomationCore.src.automation_helper.AutomationHelper;
import AutomationCore.src.automation_helper.UserData;
import DataBase.src.Persister;


public class CodeForcesAutomation {

    private static final String tableName = "codeforces_data";

    private static String trimtheString(String data)
    {
        if(data.isEmpty()) {
            return data;
        }
        StringBuilder trimmedString = new StringBuilder();
        for(int i = 0; i < data.length(); i++) {
            if(data.charAt(i) == ' ') {
                break;
            }
            trimmedString.append(data.charAt(i));
        }
        return trimmedString.toString();
    }

    public static void startTheAutomation(String userName)
    {
        UserData userData = AutomationHelper.getTheUserData(CodeForcesAutomation.class , userName);
        String userSolvedCount = trimtheString(userData.getUserSolvedCount());
        String userContestRating = userData.getUserContestRating();
        Persister.getPersisterInstance().insertIntoDataBase(tableName , userName ,
                userSolvedCount , userContestRating);
    }
}
