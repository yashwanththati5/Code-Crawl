package AutomationCore.src.automation_code;

import AutomationCore.src.automation_helper.AutomationHelper;
import AutomationCore.src.automation_helper.UserData;
import DataBase.src.Persister;

public class LeetCodeAutomation {

    private static final String tableName = "leetcode_data";

    public static void startTheAutomation(String userName) {
        UserData userData = AutomationHelper.getTheUserData(LeetCodeAutomation.class , userName);
        String userSolvedCount = userData.getUserSolvedCount();
        String userContestRating = userData.getUserContestRating();
        Persister.getPersisterInstance().insertIntoDataBase(tableName , userName , userSolvedCount
                , userContestRating);
    }
}
