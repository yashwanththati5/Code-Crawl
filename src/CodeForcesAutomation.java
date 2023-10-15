public class CodeForcesAutomation implements IAutomationForLCC {
    private String websiteUrl = Data.getCodeforcesUrl();

    private String trimtheString(String data)
    {
        if(data.equals("")) return data;
        StringBuilder trimmedString = new StringBuilder();
        for(int i = 0; i < data.length(); i++)
        {
            if(data.charAt(i) == ' ') break;
            trimmedString.append(data.charAt(i));
        }
        return trimmedString.toString();
    }
    @Override
    public void addTheSolvedCountData() {

        String xpathForSolvedCount = "//*[@id=\"pageContent\"]/div[4]/div/div[3]/div[1]/div[1]/div[1]";
        FetchTheData.userData.add(trimtheString(GetTheDataFromXPath.getTheDataFromXpath(xpathForSolvedCount)));
    }

    @Override
    public void addTheContestRatingData() {
        String xpathForContestRating = "//*[@id=\"pageContent\"]/div[2]/div/div[2]/ul/li[1]/span[1]";
        FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForContestRating));
    }
    public void startTheAutomation(String userName)
    {
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
            ErrorPopUp.startErrorPopUp("SomeThing Went Wrong" , "CodeForces Parameter");
        }
    }
}
