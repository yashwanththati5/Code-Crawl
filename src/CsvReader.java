import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader{
    private CSVReader reader = null;
    static List<String> firstRowData;
    private static int leetcodeColumnIndex = -1;
    private static int codeforcesColumnIndex = -1;
    private static int codechefColumnIndex = -1;
    private static int geekforgeeksColumnIndex = -1;

    public static int getLeetcodeColumnIndex() {
        return leetcodeColumnIndex;
    }

    public static int getCodeforcesColumnIndex() {
        return codeforcesColumnIndex;
    }

    public static int getCodechefColumnIndex() {
        return codechefColumnIndex;
    }

    public static int getGeekforgeeksColumnIndex() {
        return geekforgeeksColumnIndex;
    }
    private void getTheFirstRowData(String[] data)
    {
        firstRowData = new ArrayList<>();
        for (String dataIndexValue : data) {
            if (!dataIndexValue.equals("")) {
                firstRowData.add(dataIndexValue);
                if (Data.getLeetcodeColumnName().equals(dataIndexValue)) {
                    firstRowData.add("LeetCode_SolvedCount");
                    firstRowData.add("LeetCode_ContestRating");
                } else if (Data.getCodechefColumnName().equals(dataIndexValue)) {
                    firstRowData.add("CodeChef_SolvedCount");
                    firstRowData.add("CodeChef_ContestRating");
                } else if (Data.getCodeforcesColumnName().equals(dataIndexValue)) {
                    firstRowData.add("CodeForces_SolvedCount");
                    firstRowData.add("CodeForces_ContestRating");
                } else if (Data.getGeekforgeeksColumnName().equals(dataIndexValue)) {
                    firstRowData.add("GeekForGeeks_SolvedCount");
                    firstRowData.add("GeekForGeeks_CodingScore");
                }
            }
            else firstRowData.add("");
        }
    }
    private void checkingColumnNameAndIndex()
    {
        try
        {
            if(leetcodeColumnIndex == -1 && !(Data.getLeetcodeColumnName().isEmpty())) throw new Exception("LeetCode");
            if(codechefColumnIndex == -1 && !(Data.getCodechefColumnName().isEmpty())) throw new Exception("CodeChef");
            if(geekforgeeksColumnIndex == -1 && !(Data.getGeekforgeeksColumnName().isEmpty())) throw new Exception("GeekforGeeks");
            if(codeforcesColumnIndex == -1 && !(Data.getCodeforcesColumnName().isEmpty())) throw new Exception("CodeForces");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String errorTitle = "Missing column in " + Data.getInputFileName();
            String error = "(" + Data.getInputFileName() + ") " + e.getMessage() + " Column Missing";
            ErrorPopUp.startErrorPopUp(errorTitle , error);
        }
    }
    private void getTheWebsiteColumnIndex(String[] data)
    {
        for(int i = 0; i < data.length; i++) {
            if (!data[i].equals("")) {
                if (Data.getLeetcodeColumnName().equals(data[i])) leetcodeColumnIndex = i;
                if (Data.getCodechefColumnName().equals(data[i])) codechefColumnIndex = i;
                if (Data.getCodeforcesColumnName().equals(data[i])) codeforcesColumnIndex = i;
                if (Data.getGeekforgeeksColumnName().equals(data[i])) geekforgeeksColumnIndex = i;
            }
        }
        checkingColumnNameAndIndex();
    }
    private void readTheDataFromTheFile(String FilePath)
    {
        try
        {
            List<String[]> usernamesData;
            usernamesData = reader.readAll();
            getTheWebsiteColumnIndex(usernamesData.get(0));
            Data.setUsernameData(usernamesData);
            getTheFirstRowData(usernamesData.get(0));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("Can't Read the Data_src.Data from the File" , FilePath);
        }
    }
    private void checkFileExtension() {
        boolean ans;
        String fileName = Data.getInputFileName();
        try {
            int ind = fileName.indexOf('.');
            ans = fileName.substring(ind).equals(".csv");
            if(!ans) throw new Exception("Extension of is not valid");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            String errorMsg = "(" + fileName + ") No File Extension or Invalid (Refer to Parameter File)";
            ErrorPopUp.startErrorPopUp("Invalid File Extension" , errorMsg);
        }
    }
    public void startCsvreader() {
        String FilePath = "";
        try {
            FilePath = Data.getInputFilePath() + Data.getInputFileName();
            checkFileExtension();
            reader = new CSVReader(new FileReader(FilePath));
            readTheDataFromTheFile(FilePath);
            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("Can't Read Input File or File Not Found" , FilePath);
        }
    }
}
