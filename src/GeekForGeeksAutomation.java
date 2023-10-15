public class GeekForGeeksAutomation implements IAutomationForGeeks {
    String websiteUrl = Data.getGeekforgeeksUrl();
    String xpathForSolvedCount = "/html/body/div[6]/div/div[2]/div[1]/div/div[3]/div/div[2]/div[2]/div/div/span[2]";
    String xpathForCodingScore = "/html/body/div[6]/div/div[2]/div[1]/div/div[3]/div/div[2]/div[1]/div/div/span[2]";
    @Override
     public void addTheSolvedCountData() {
        FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForSolvedCount));
    }

    @Override
    public void addTheCodingScoreData() {
        FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForCodingScore));
    }
    public void startTheAutomation(String userName)
    {
        websiteUrl = websiteUrl + userName + "/";
        try {
            DriverAndWebdriverSetup.driver.get(websiteUrl);
            FetchTheData.userData.add(userName);
            addTheSolvedCountData();
            addTheCodingScoreData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("SomeThing Went Wrong" , "GeekForGeeks Parameter");
        }
    }
}
