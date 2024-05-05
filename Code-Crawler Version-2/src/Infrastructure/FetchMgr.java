package Infrastructure;

import AutomationCore.src.automation_helper.UserData;
import DataBase.src.Persister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchMgr {

    private static Map<String , UserData> leetcode_data;

    private static Map<String , UserData> codechef_data;

    private static Map<String , UserData> codeforces_data;

    private static Map<String , UserData> geeksforgeeks_data;

    public void init() {
        leetcode_data = new HashMap<>();
        codechef_data = new HashMap<>();
        codeforces_data = new HashMap<>();
        geeksforgeeks_data = new HashMap<>();

        Persister persister = Persister.getPersisterInstance();

        List<String> leetcode_userNames = InputFileUserDataParser.getLeetcode_usernames();
        leetcode_userNames.forEach(userName -> {
            UserData userData = persister.fetchQuery("leetcode_data" , userName);
            leetcode_data.put(userName  , userData);
        });

        List<String> codechef_userNames = InputFileUserDataParser.getCodechef_usernames();
        codechef_userNames.forEach(userName -> {
            UserData userData = persister.fetchQuery("codechef_data" , userName);
            codechef_data.put(userName  , userData);
        });

        List<String> codeforces_userNames = InputFileUserDataParser.getCodeforces_usernames();
        codeforces_userNames.forEach(userName -> {
            UserData userData = persister.fetchQuery("codeforces_data" , userName);
            codeforces_data.put(userName  , userData);
        });

        List<String> geeksforgeeks_userNames = InputFileUserDataParser.getGeeksforgeeks_usernames();
        geeksforgeeks_userNames.forEach(userName -> {
            UserData userData = persister.fetchQuery("geeksforgeeks_data" , userName);
            geeksforgeeks_data.put(userName  , userData);
        });
    }
    public List<List<String>> getTheData() {
        if(leetcode_data == null || codeforces_data == null ||
                codechef_data == null || geeksforgeeks_data == null) {
            init();
        }
        List<List<String>> data = new java.util.ArrayList<>(List.of());
        List<String[]> entireData = Data.getUsernameData();
        for(int i = 1; i < entireData.size(); i++) {
            String[] dataRow = entireData.get(i);
            List<String> userDataRow = getTheUserDataRow(dataRow);
            data.add(userDataRow);
        }
        return data;
    }

    private List<String> getTheUserDataRow(String[] userDataRow) {
        List<String> dataRow = new ArrayList<>();
        for(int i = 0; i < userDataRow.length; i++) {
            String columnValue = userDataRow[i];
            dataRow.add(columnValue);
            UserData userData = null;
            if(isLeetCodeIndex(i)) {
                userData = leetcode_data.get(columnValue);
            }
            else if(isCodeChefIndex(i)) {
                userData = codechef_data.get(columnValue);
            }
            else if(isCodeForcesIndex(i)) {
                userData = codeforces_data.get(columnValue);
            }
            else if(isGeeksForGeeksIndex(i)) {
                userData = geeksforgeeks_data.get(columnValue);
            }

            if(userData != null) {
                addUserDataToTheList(dataRow , userData);
            }
        }
        return dataRow;
    }

    private void addUserDataToTheList(List<String> data , UserData userData) {
        data.add(userData.getUserSolvedCount());
        data.add(userData.getUserContestRating());
    }

    private boolean isLeetCodeIndex(int index) {
        return index == InputFileParser.getLeetcodeColumnIndex();
    }

    private boolean isCodeChefIndex(int index) {
        return index == InputFileParser.getCodechefColumnIndex();
    }

    private boolean isCodeForcesIndex(int index) {
        return index == InputFileParser.getCodeforcesColumnIndex();
    }

    private boolean isGeeksForGeeksIndex(int index) {
        return index == InputFileParser.getGeeksforgeeksColumnIndex();
    }
}
