package AutomationCore.src.automation_helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GetTheDataFromXPath {
    public static String getTheDataFromXpath(String Xpath)
    {
        String dataFromXpath = "";
        try
        {
            WebElement element = DriverAndWebDriverSetup.wait.until(ExpectedConditions.presenceOfElementLocated
                    (By.xpath(Xpath)));
            dataFromXpath = element.getText();
        }
        catch(Exception ignored) {}
        return dataFromXpath;
    }
}
