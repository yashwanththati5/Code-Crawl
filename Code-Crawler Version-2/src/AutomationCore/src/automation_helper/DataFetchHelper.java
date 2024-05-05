package AutomationCore.src.automation_helper;

public class DataFetchHelper {

    private static String className;

    private static DataFetchHelper dataFetchHelper;

    public static DataFetchHelper getInstance(){
        if(dataFetchHelper == null){
            dataFetchHelper = new DataFetchHelper();
        }
        return dataFetchHelper;
    }

    public UserData getUserData(Object classObject)
    {
        className = ((Class<?>) classObject).getSimpleName();
        String solvedCountXpath = getTheSolvedCountXpath(classObject);
        String contestRatingXpath = getTheContestRatingXpath(classObject);
        return getUserData(solvedCountXpath , contestRatingXpath);
    }

    private UserData getUserData(String solvedCountXpath , String contestRatingXpath) {
        String solvedCount = GetTheDataFromXPath.getTheDataFromXpath(solvedCountXpath);
        String contestRating = GetTheDataFromXPath.getTheDataFromXpath(contestRatingXpath);
        return new UserData(solvedCount , contestRating);
    }

    private String getTheContestRatingXpath(Object classObject) {
        String resourceContestRatingNotation = "contestRating.xpath";
        String resourceString = className.concat("." + resourceContestRatingNotation);
        return ResourceBundleHelper.getString(classObject , resourceString);
    }

    private String getTheSolvedCountXpath(Object classObject) {
        String resourceSolvedCountNotation = "solvedCount.xpath";
        String resourceString = className.concat("." + resourceSolvedCountNotation);
        return ResourceBundleHelper.getString(classObject , resourceString);
    }
}
