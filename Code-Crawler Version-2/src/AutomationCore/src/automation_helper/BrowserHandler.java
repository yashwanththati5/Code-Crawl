package AutomationCore.src.automation_helper;

import Infrastructure.Environment;

public class BrowserHandler {

    private final String CODECHEF_URL = Environment.getCodeChefUrl();

    private final String LEETCODE_URL = Environment.getLeetcodeUrl();

    private final String CODEFORCES_URL = Environment.getCodeForcesUrl();

    private final String GEEKSFORGEEKS_URL = Environment.getGeeksForGeeksUrl();

    private static BrowserHandler browserHandler;

    private static DriverAndWebDriverSetup driverAndWebdriverSetup;

    public BrowserHandler() {
        driverAndWebdriverSetup = new DriverAndWebDriverSetup();
        driverAndWebdriverSetup.setupTheDriverAndWebDriver();
    }
    public static BrowserHandler getInstance()
    {
        if(browserHandler == null){
            browserHandler = new BrowserHandler();
        }
        return browserHandler;
    }
    public void loadThePage(String className , String userName){
        String websiteUrl = getTheLoadUrl(className , userName);
        DriverAndWebDriverSetup.driver.get(websiteUrl);
    }

    private String getTheLoadUrl(String className, String userName) {
        String url = getTheWebsiteUrl(className);
        url = url.concat(userName + "/");
        return url;
    }

    private String getTheWebsiteUrl(String className) {
        return switch (className) {
            case "CodeChefAutomation" -> CODECHEF_URL;
            case "CodeForcesAutomation" -> CODEFORCES_URL;
            case "LeetCodeAutomation" -> LEETCODE_URL;
            case "GeeksForGeeksAutomation" -> GEEKSFORGEEKS_URL;
            default -> "";
        };
    }

    protected void closeTheDriver() {
        driverAndWebdriverSetup.stopTheDriver();
    }
}
