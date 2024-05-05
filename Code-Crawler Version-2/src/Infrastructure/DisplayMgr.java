package Infrastructure;

import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class DisplayMgr {

    public void start() {
        int port = 5543;
        createHttpServer(port);
        MessageHelper.showMessage(DisplayMgr.class , "DisplayMgr.showSuccessDialog" ,
                JOptionPane.INFORMATION_MESSAGE , false , "http://localhost:5543/Report.html");
    }

    private void createHttpServer(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Server is running on http://localhost:5543/Report.html");
            server.createContext("/Report.html", exchange -> {
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, 0);
                OutputStream responseBody = exchange.getResponseBody();
                String staticContent = getTheStaticContent();
                String dynamicContent = getTheDynamicContent();
                String downloadButton = getTheDownloadButton();
                responseBody.write(staticContent.getBytes());
                responseBody.write(downloadButton.getBytes());
                responseBody.write(dynamicContent.getBytes());
                responseBody.close();
            });
            server.start();
        }
        catch (IOException e) {
            MessageHelper.showMessage(DisplayMgr.class , "DisplayMgr.ServerCreationFailed");
        }
    }

    private String getTheDownloadButton() {
        return """
                <div class="download-container">
                               <button class="download-button" onclick="downloadCSV()">
                                   <span class="download-icon"></span> Download In CSV
                               </button>
                </div>
                <div style="margin-top: 50px;"></div>
                """;
    }

    private String getTheDynamicContent() {
        StringBuilder dynamicContent = new StringBuilder();
        dynamicContent.append("<table ");
        dynamicContent.append("id = \"myTable\">");
        String header = getTheDynamicHeader();
        dynamicContent.append(header);
        dynamicContent.append(System.lineSeparator());
        String dynamicRows = getTheDynamicDataRows();
        dynamicContent.append(dynamicRows);
        dynamicContent.append("</table>");
        return dynamicContent.toString();
    }

    private String getTheDynamicDataRows() {
        StringBuilder dynamicTableRows = new StringBuilder();
        FetchMgr fetchMgr = new FetchMgr();
        List<List<String>> userData = fetchMgr.getTheData();
        for (List<String> userDataRow : userData) {
            dynamicTableRows.append("<tr>");
            for (String userDataColumn : userDataRow) {
                dynamicTableRows.append("<td>");
                dynamicTableRows.append(userDataColumn);
                dynamicTableRows.append("</td>");
            }
            dynamicTableRows.append("</tr>");
            dynamicTableRows.append("\n");
        }
        return dynamicTableRows.toString();
    }

    private String getTheDynamicHeader() {
        return getTheHTMLDynamicHeader();
    }

    private String getTheHTMLDynamicHeader() {
        StringBuilder htmlTableHeader = new StringBuilder();
        htmlTableHeader.append("<tr>");
        List<String> userFileHeader = Data.getHeader();
        List<String> reportHeader = new ArrayList<>();
        for (String dataIndexValue : userFileHeader) {
            reportHeader.add(dataIndexValue);
            if(isLeetCodeColumnName(dataIndexValue)) {
                reportHeader.add("LeetCode_SolvedCount");
                reportHeader.add(("LeetCode_ContestRating"));
            }
            else if(isCodeChefColumnName(dataIndexValue)) {
                reportHeader.add("CodeChef_SolvedCount");
                reportHeader.add("CodeChef_ContestRating");
            }
            else if(isCodeForcesColumnName(dataIndexValue)) {
                reportHeader.add("CodeForces_SolvedCount");
                reportHeader.add("CodeForces_ContestRating");
            }
            else if(isGeeksForGeeksColumnName(dataIndexValue)) {
                reportHeader.add("GeeksForGeeks_SolvedCount");
                reportHeader.add("GeeksForGeeks_CodingScore");
            }
        }
        for(String value : reportHeader) {
            htmlTableHeader.append("<th>");
            htmlTableHeader.append(value);
            htmlTableHeader.append("</th>");
        }
        htmlTableHeader.append("</tr>");
        return htmlTableHeader.toString();
    }

    private boolean isLeetCodeColumnName(String dataIndexValue) {
        return Environment.getLeetcodeColumnName().equals(dataIndexValue);
    }

    private boolean isCodeChefColumnName(String dataIndexValue) {
        return Environment.getCodeChefColumnName().equals(dataIndexValue);
    }

    private boolean isCodeForcesColumnName(String dataIndexValue) {
        return Environment.getCodeForcesColumnName().equals(dataIndexValue);
    }

    private boolean isGeeksForGeeksColumnName(String dataIndexValue) {
        return Environment.getGeeksForGeeksColumnName().equals(dataIndexValue);
    }

    private String getTheStaticContent() {
        String htmlContent = readTheHTMLContentFromFile();
        if(!htmlContent.isEmpty()) {
            return htmlContent;
        }
        return "";
    }

    private String readTheHTMLContentFromFile()
    {
        String htmlFilePath = "src/Infrastructure/Resource/index.html";
        StringBuilder htmlContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(htmlFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
                htmlContent.append(System.lineSeparator());
            }
        } catch (IOException e) {
            MessageHelper.showMessage(DisplayMgr.class , "DisplayMgr.ResourceFileMissing");
        }
        return htmlContent.toString();
    }
}
