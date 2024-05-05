import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter{
    public static boolean isWriterOpen = false;
    public static CSVWriter writer = null;
    private String[] convertListStringToStringArray(List<String> data)
    {
        String[] converteddata = new String[data.size()];
        for(int i = 0; i < data.size(); i++){
            String s = data.get(i);
            if(s.equals("")) converteddata[i] = "0";
            else converteddata[i] = s;
        }
        return converteddata;
    }
    public void writeTheFirstRow()
    {
        String[] firstRowData = convertListStringToStringArray(CsvReader.firstRowData);
        writer.writeNext(firstRowData);
    }
    public void writeTheDataToTheFile()
    {
        String[] userDatastr = convertListStringToStringArray(FetchTheData.userData);
        writer.writeNext(userDatastr);
    }
    protected void clearTheListData()
    {
        FetchTheData.userData.clear();
    }
    private String removeFileExtension(int ind , String fileName)
    {
        fileName = fileName.substring(0 , ind);
        return fileName;
    }
    private void checkFileNameAndExtension()
    {
        String fileName = Data.getOutputFileName();
        if(fileName.equals("")) fileName = "output";
        boolean ans = false;
        int ind = fileName.indexOf('.');
        if(ind != -1) ans = fileName.substring(ind).equals(".csv");
        if(ind != -1 && !ans) fileName = removeFileExtension(ind , fileName);
        if(!ans) {
            fileName = fileName.concat(".csv");
            Data.setOutputFileName(fileName);
        }
    }

    public void openTheWriter()
    {
        String FilePath = "";
        String FileName;
        try {
            checkFileNameAndExtension();
            checkFilePath();
            FilePath = Data.getOutputFilePath();
            FileName = Data.getOutputFileName();
            if(FilePath.charAt(FilePath.length() - 1) != '/') FilePath.concat("/");
            FilePath += FileName;
            writer = new CSVWriter(new FileWriter(FilePath));
            isWriterOpen = true;
        }
        catch(Exception e)
        {
            ErrorPopUp.startErrorPopUp("Can't Write , File Location Not Found" , FilePath);
        }
    }

    private void checkFilePath() {
        if(Data.getOutputFilePath().equals(""))
        {
            Path path = Paths.get("Parameters.xml");
            String p = path.toAbsolutePath().toString();
            int ind = 0;
            for(int i = p.length() - 1; i >= 0; i--)
            {
                if(p.charAt(i) == '/'){ind = i; break;}
            }
            p = p.substring(0 , ind + 1);
            Data.setOutputFilePath(p);
        }
    }

    public void closeTheWriter() throws IOException {
        writer.close();
        isWriterOpen = false;
    }
}
