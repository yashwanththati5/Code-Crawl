package Infrastructure;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class InputFileParser {

    private static int leetcodeColumnIndex = -1;

    private static int codechefColumnIndex = -1;

    private static int codeforcesColumnIndex = -1;

    private static int geeksforgeeksColumnIndex = -1;

    public void init()
    {
        processTheInputFile();
        parseTheHeaderFromInputFile();
        new InputFileUserDataParser().initDataParser();
    }

    private void parseTheHeaderFromInputFile() {
        List<String> header = Data.getHeader();
        getTheAutomationTagIndexes(header);
        validateTheAutomationTagIndexes();
    }

    private void validateTheAutomationTagIndexes() {
        validateTheAutomationTagIndexes(leetcodeColumnIndex , Environment.getLeetcodeColumnName() ,
                "leetcode");
        validateTheAutomationTagIndexes(codechefColumnIndex , Environment.getCodeChefColumnName() ,
                "codechef");
        validateTheAutomationTagIndexes(codeforcesColumnIndex , Environment.getCodeForcesColumnName() ,
                "codeforces");
        validateTheAutomationTagIndexes(geeksforgeeksColumnIndex , Environment.getGeeksForGeeksColumnName() ,
                "geeksforgeeks");
    }

    private void validateTheAutomationTagIndexes(int index, String columnName, String parameterName) {
        if(index == -1 && !(columnName.isEmpty())){
            MessageHelper.showMessage(InputFileParser.class , "InputFileParser.ColumnNameNotFound" , parameterName);
        }
    }

    private void getTheAutomationTagIndexes(List<String> header) {
        for(int index = 0; index < header.size(); index++) {
            getTheAutomationIndexes(index , header.get(index));
        }
    }

    private void getTheAutomationIndexes(int dataIndex , String columnName) {

        if(columnName.equals(Environment.getLeetcodeColumnName())) {
            leetcodeColumnIndex = dataIndex;
        }
        if(columnName.equals(Environment.getCodeChefColumnName())) {
            codechefColumnIndex = dataIndex;
        }
        if(columnName.equals(Environment.getCodeForcesColumnName())) {
            codeforcesColumnIndex = dataIndex;
        }
        if(columnName.equals(Environment.getGeeksForGeeksColumnName())) {
            geeksforgeeksColumnIndex = dataIndex;
        }
    }

    private void processTheInputFile() {
        CSVReader reader = getTheReader();
        readTheDataFromInputFile(reader);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readTheDataFromInputFile(CSVReader reader) {
        try {
            List<String[]> fileData = reader.readAll();
            Data.setUsernameData(fileData);
            extractTheHeaderFromFileData(fileData);
        } catch (IOException e) {
            MessageHelper.showMessage(InputFileParser.class , "InputFileParser.ReadInputFileFailed" , Environment.getInputFileName());
        }
    }

    private void extractTheHeaderFromFileData(List<String[]> fileData) {
        List<String> header = Arrays.stream(fileData.getFirst()).toList();
        Data.setHeader(header);
    }

    private CSVReader getTheReader() {
        String fileName = Environment.getInputFileName();
        String filePath = Environment.getInputFilePath();

        isValidFileName(fileName);
        String inputFile = Path.of(filePath , fileName).toString();

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            String inputFilePath = Environment.getInputFilePath() + File.pathSeparator + Environment.getInputFileName();
            String extension = ".csv";
            MessageHelper.showMessage(InputFileParser.class , "InputFileParser.InputFileNotFound" , inputFilePath , extension);
        }
        return reader;
    }
    private void isValidFileName(String fileName) {
        boolean ans;
        String requiredFileExtension = ".csv";
        ans = fileName.contains(requiredFileExtension);
        if(!ans) {
            MessageHelper.showMessage(InputFileParser.class , "InputFileParser.FileExtensionMisMatch" , fileName);
        }
    }

    public static int getLeetcodeColumnIndex() {
        return leetcodeColumnIndex;
    }

    public static int getCodechefColumnIndex() {
        return codechefColumnIndex;
    }

    public static int getCodeforcesColumnIndex() {
        return codeforcesColumnIndex;
    }

    public static int getGeeksforgeeksColumnIndex() {
        return geeksforgeeksColumnIndex;
    }

}
