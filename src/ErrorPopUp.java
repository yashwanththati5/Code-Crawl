import javax.swing.*;

public class ErrorPopUp {
    public static void startErrorPopUp(String errorTitle, String errorMessage) {
        String error = "You Missed Something In " + errorMessage;
        JOptionPane.showMessageDialog(null, error, errorTitle, JOptionPane.OK_OPTION);
        if (DriverAndWebdriverSetup.isDriverOpen) {
            DriverAndWebdriverSetup.driver.close();
        }
        if (CsvWriter.isWriterOpen) {
            try {
                CsvWriter.writer.close();
            }
            catch (Exception ignored){}
        }
        System.exit(0);
    }
}
