import AutomationCore.src.automation_helper.AutomationMgr;
import Infrastructure.*;

public class StartTheAutomation {
    public static void start(){
        try {
            Environment.setup();
            new InputFileParser().init();
            new AutomationMgr().startAutomation();
        }
        catch (Exception ignored){
        }
    }
    public static void main(String[] args){
        StartTheAutomation.start();
    }
}
