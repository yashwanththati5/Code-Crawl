package Infrastructure;

import java.util.List;

public class Data {

    private static List<String> header;
    private static List<String[]> usernameData;
    public static List<String[]> getUsernameData() {
        return usernameData;
    }

    public static void setUsernameData(List<String[]> usernameData) {
        Data.usernameData = usernameData;
    }

    public static List<String> getHeader() {
        return header;
    }

    public static void setHeader(List<String> header) {
        Data.header = header;
    }
}
