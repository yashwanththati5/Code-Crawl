import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser{
    DocumentBuilderFactory builderFactory;
    DocumentBuilder builder;
    Document document;
    private void getInputOutputFileNameAndPath(String TagName){
        NodeList nodeData = document.getElementsByTagName(TagName);
        if(nodeData.getLength() == 0) ErrorPopUp.startErrorPopUp(TagName + " File Error" , "Parameters.xml / " + TagName);
        this.getInputOutputNodeListData(nodeData , TagName);
    }

    private void getInputOutputNodeListData(NodeList List , String TagName)
    {
        String filePath = "" , fileName = "";
        for(int i = 0; i < List.getLength(); i++)
        {
            Node nodeData = List.item(i);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elementData = (Element) nodeData;
                filePath = elementData.getAttribute("path");
                fileName = elementData.getAttribute("filename");
            }
        }
        if(TagName.equals("InputFilePath"))
        {
            if(fileName.equals("") || filePath.equals("")) ErrorPopUp.startErrorPopUp("Invalid Input Paramters" , "Parameters.xml File No Input File To Read");
           Data.setInputFilePath(filePath);
           Data.setInputFileName(fileName);
        }
        if(TagName.equals("OutputFilePath"))
        {
            Data.setOutputFilePath(filePath);
            Data.setOutputFileName(fileName);
        }
    }
    private void setWebsiteUrlAndColumnName(Element element , String webSiteName){
        String webSiteUrl = element.getAttribute("url");
        String webSiteColumnName = element.getAttribute("column_name");
        if(webSiteUrl.isEmpty() || webSiteColumnName.isEmpty())
        {
            String x = webSiteUrl.isEmpty() ? "URl" : "column_name";
            String Error = "Parameters.xml / websitename : " + x;
            String errorTitle = "Error in " + webSiteName + " attributes";
            ErrorPopUp.startErrorPopUp(errorTitle , Error);
        }
        if(webSiteName.equals("LeetCode"))
        {
            Data.setLeetcodeUrl(element.getAttribute("url"));
            Data.setLeetcodeColumnName(element.getAttribute("column_name"));
        }
        if(webSiteName.equals("CodeChef"))
        {
            Data.setCodechefUrl(element.getAttribute("url"));
            Data.setCodechefColumnName(element.getAttribute("column_name"));
        }
        if(webSiteName.equals("CodeForces"))
        {
            Data.setCodeforcesUrl(element.getAttribute("url"));
            Data.setCodeforcesColumnName(element.getAttribute("column_name"));
        }
        if(webSiteName.equals("GeeksForGeeks"))
        {
            Data.setGeekforgeeksUrl(element.getAttribute("url"));
            Data.setGeekforgeeksColumnName(element.getAttribute("column_name"));
        }
    }
    private void getWebsiteNodeListData(NodeList List) {
        for(int i = 0; i < List.getLength(); i++)
        {
            Node nodeData = List.item(i);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elementData = (Element) nodeData;
                String webSiteName = elementData.getAttribute("nameOfTheWebsite");
                setWebsiteUrlAndColumnName(elementData , webSiteName);
            }
        }
    }
    public void startXMLParser(){
        builderFactory = DocumentBuilderFactory.newInstance();
        try
        {
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(new File("Parameters.xml"));
            document.getDocumentElement().normalize();
            this.getInputOutputFileNameAndPath("InputFilePath");
            this.getInputOutputFileNameAndPath("OutputFilePath");
            NodeList websiteData = document.getElementsByTagName("websitename");
            this.getWebsiteNodeListData(websiteData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ErrorPopUp.startErrorPopUp("Error in XML Parsing" , "Parameters.xml");
        }
    }
}
