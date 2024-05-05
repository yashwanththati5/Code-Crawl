package AutomationCore.src.automation_helper;

import AutomationCore.src.automation_code.CodeChefAutomation;
import AutomationCore.src.automation_code.CodeForcesAutomation;
import AutomationCore.src.automation_code.GeeksForGeeksAutomation;
import AutomationCore.src.automation_code.LeetCodeAutomation;
import Infrastructure.DisplayMgr;
import Infrastructure.InputFileUserDataParser;

import java.util.List;

public class AutomationMgr {

    public void startAutomation() {
        List<String> leetcode_usernames = InputFileUserDataParser.getLeetcode_usernames();
        List<String> codechef_usernames = InputFileUserDataParser.getCodechef_usernames();
        List<String> codeforces_usernames = InputFileUserDataParser.getCodeforces_usernames();
        List<String> geeksforgeeks_usernames = InputFileUserDataParser.getGeeksforgeeks_usernames();

        leetcode_usernames.forEach(LeetCodeAutomation::startTheAutomation);
        codechef_usernames.forEach(CodeChefAutomation::startTheAutomation);
        codeforces_usernames.forEach(CodeForcesAutomation::startTheAutomation);
        geeksforgeeks_usernames.forEach(GeeksForGeeksAutomation::startTheAutomation);

        BrowserHandler.getInstance().closeTheDriver();
        new DisplayMgr().start();
    }
}
