package Infrastructure;

import AutomationCore.src.automation_helper.DriverAndWebDriverSetup;
import AutomationCore.src.automation_helper.ResourceBundleHelper;

import javax.swing.*;
import java.text.MessageFormat;


public class MessageHelper {

    private static final String defaultTitle = "title";

    private static final String defaultContext = "context";

    private static final int defaultDialogType = JOptionPane.ERROR_MESSAGE;

    public static void showMessage(Object object , String key) {
        String dialogTitle = getTheDialogTitle(object , key);
        String dialogContext = getTheContext(object , key);
        showMessageDialog(dialogTitle , dialogContext , defaultDialogType , true);
    }

    public static void showMessage(Object object , String key , String ...values) {
        String dialogTitle = getTheDialogTitle(object , key);
        String dialogContext = getTheContext(object , key);
        dialogContext = MessageFormat.format(dialogContext , values);
        showMessageDialog(dialogTitle , dialogContext , defaultDialogType , true);
    }

    public static void showMessage(Object object , String key , int dialogType , boolean shouldExit , String ...values) {
        String dialogTitle = getTheDialogTitle(object , key);
        String dialogContext = getTheContext(object , key);
        dialogContext = MessageFormat.format(dialogContext , values);
        showMessageDialog(dialogTitle , dialogContext , dialogType , shouldExit);
    }

    private static void showMessageDialog(String dialogTitle, String dialogContext , int dialogType , boolean shouldExit) {
        JOptionPane.showMessageDialog(null, dialogContext , dialogTitle ,
                dialogType);
        if (DriverAndWebDriverSetup.isDriverOpen) {
            DriverAndWebDriverSetup.driver.close();
        }
        if(shouldExit){
            System.exit(dialogType);
        }
    }

    private static String getTheContext(Object object, String key) {
        String contextKey = key.concat("." + defaultContext);
        return ResourceBundleHelper.getString(object , contextKey);
    }

    private static String getTheDialogTitle(Object object , String key) {
        String titleKey = key.concat("." + defaultTitle);
        return ResourceBundleHelper.getString(object , titleKey);
    }
}
