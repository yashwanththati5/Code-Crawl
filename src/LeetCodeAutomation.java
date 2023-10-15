public class LeetCodeAutomation implements IAutomationForLCC {
     String websiteUrl = Data.getLeetcodeUrl();
     String xpathForSolvedCount = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/div[3]/div[1]/div/div[2]/div[1]/div/div/div/div[1]";
     String xpathForCotestRating = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/div/div[1]/div[2]";
     public void addTheSolvedCountData()
     {
         FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForSolvedCount));
     }
     public void addTheContestRatingData()
     {
         FetchTheData.userData.add(GetTheDataFromXPath.getTheDataFromXpath(xpathForCotestRating));
     }
    public void startTheAutomation(String userName) {
        websiteUrl = websiteUrl + userName + "/";
        try {
            DriverAndWebdriverSetup.driver.get(websiteUrl);
            FetchTheData.userData.add(userName);
            addTheSolvedCountData();
            addTheContestRatingData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("SomeThing Went Wrong" , "LeetCode Parameter");
        }
    }
}
