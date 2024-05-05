package AutomationCore.src.automation_helper;

public final class UserData {

    private static final String defaultValue = "0";

    private String userSolvedCount;

    private String userContestRating;

    public UserData(String userSolvedCount , String userContestRating)
    {
        this.userSolvedCount = userSolvedCount;
        this.userContestRating = userContestRating;
    }

    public String getUserSolvedCount(){
        if(userSolvedCount.isEmpty()) {
            userSolvedCount = defaultValue;
        }
        return userSolvedCount;
    }

    public String getUserContestRating() {
        if(userContestRating.isEmpty()) {
            userContestRating = defaultValue;
        }
        return userContestRating;
    }
}
