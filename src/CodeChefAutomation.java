public class CodeChefAutomation implements IAutomationForLCC {
    private String websiteUrl = Data.getCodechefUrl();

    private String trimtheString(String data)
    {
        if(data.equals("")) return data;
        StringBuilder trimmedString = new StringBuilder();
        for(int i = data.indexOf('(') + 1; i < data.indexOf(')'); i++)
        {
            trimmedString.append(data.charAt(i));
        }
        return trimmedString.toString();
    }
    @Override
    public void addTheSolvedCountData() {
        String xpathForSolvedCount = "/html/body/main/div/div/div/div/div/section[6]/h3[1]";
        FetchTheData.userData.add(trimtheString(GetTheDataFromXPath.getTheDataFromXpath(xpathForSolvedCount)));
    }

    @Override
    public void addTheContestRatingData() {
        String xpathForContestRating = "/html/body/main/div/div/div/aside/div[1]/div/div[1]/div[1]";
        FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForContestRating));
    }
    public void startTheAutomation(String userName) {
        websiteUrl =  websiteUrl + userName + "/";
        try {
            DriverAndWebdriverSetup.driver.get(websiteUrl);
            FetchTheData.userData.add(userName);
            addTheSolvedCountData();
            addTheContestRatingData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("SomeThing Went Wrong" , "CodeChef Parameter");
        }
    }
}
