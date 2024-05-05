package Infrastructure;


import java.util.ArrayList;
import java.util.List;

public class InputFileUserDataParser {

    private final int leetcodeColumnIndex = InputFileParser.getLeetcodeColumnIndex();

    private final int codechefColumnIndex = InputFileParser.getCodechefColumnIndex();

    private final int codeforcesColumnIndex = InputFileParser.getCodeforcesColumnIndex();

    private final int geeksforgeeksColumnIndex = InputFileParser.getGeeksforgeeksColumnIndex();

    private static List<String> leetcode_usernames;

    private static List<String> codeforces_usernames;

    private static List<String> codechef_usernames;

    private static List<String> geeksforgeeks_usernames;

    public void initDataParser() {
        List<String[]> entireData = Data.getUsernameData();
        leetcode_usernames = new ArrayList<>();
        codechef_usernames = new ArrayList<>();
        codeforces_usernames = new ArrayList<>();
        geeksforgeeks_usernames = new ArrayList<>();

        for(int index = 1; index < entireData.size(); index++) {
            addUserNameToRespectiveAutomation(entireData.get(index));
        }
    }

    private void addUserNameToRespectiveAutomation(String[] row) {

        for(int i = 0; i < row.length; i++) {
            addUserNameToRespectiveAutomation(i , row[i]);
        }
    }

    private void addUserNameToRespectiveAutomation(int index , String userName) {
        if(index == leetcodeColumnIndex){
            leetcode_usernames.add(userName);
        }
        if(index == codechefColumnIndex) {
            codechef_usernames.add(userName);
        }
        if(index == codeforcesColumnIndex) {
            codeforces_usernames.add(userName);
        }
        if(index == geeksforgeeksColumnIndex) {
            geeksforgeeks_usernames.add(userName);
        }
    }

    public static List<String> getLeetcode_usernames() {
        return leetcode_usernames;
    }

    public static List<String> getCodeforces_usernames() {
        return codeforces_usernames;
    }

    public static List<String> getCodechef_usernames() {
        return codechef_usernames;
    }

    public static List<String> getGeeksforgeeks_usernames() {
        return geeksforgeeks_usernames;
    }
}
