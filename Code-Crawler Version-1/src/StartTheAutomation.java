public class StartTheAutomation {
    public static void start(){
        try {
            new XMLParser().startXMLParser();
            new CsvReader().startCsvreader();
            DriverAndWebdriverSetup driverWeb = new DriverAndWebdriverSetup();
            driverWeb.setupTheDriverAndWebdriver();
            new FetchTheData().runTheAutomationOnData();
            driverWeb.stopTheDriver();
        }
        catch (Exception ignored){}
    }
    public static void main(String[] args){
        StartTheAutomation.start();
    }
}
