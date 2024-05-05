package AutomationCore.src.automation_helper;

public class AutomationHelper {

    public static UserData getTheUserData(Object object , String userName){
        String className = ((Class<?>) object).getSimpleName();
        BrowserHandler.getInstance().loadThePage(className , userName);
        UserData userData;
        userData = DataFetchHelper.getInstance().getUserData(object);
        return userData;
    }
}
