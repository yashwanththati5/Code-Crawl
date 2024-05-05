package Infrastructure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class Environment {
    private static Document document;

    private static Element inputElement;

    private static Element dbConfigurationElement;

    private static Element leetcodeElement;

    private static Element codechefElement;

    private static Element codeforcesElement;

    private static Element geeksforgeeksElement;

    private static final String defaultValue = "";

    public static void setup()
    {
        Environment.readParametersFile();
        parseTags();
    }

    private static void parseTags() {
        Environment.parseInputTag();
        Environment.parseDataBaseConfigurationTag();
        Environment.parseLeetcodeTag();
        Environment.parseCodeChefTag();
        Environment.parseCodeForcesTag();
        Environment.parseGeeksForGeeksTag();
    }

    private static void readParametersFile()
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.parse(new File("Parameters.xml"));
            document.getDocumentElement().normalize();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            MessageHelper.showMessage(Environment.class , "Environment.XMLParsingIssue");
        }
    }

    public static String getInputFilePath()
    {
        return inputElement.getAttribute("path");
    }

    public static String getInputFileName()
    {
        return inputElement.getAttribute("filename");
    }

    public static String getDatabaseConnectionUsername()
    {
        return dbConfigurationElement.getAttribute("username");
    }

    public static String getDatabaseConnectionPassword()
    {
        return dbConfigurationElement.getAttribute("password");
    }

    public static String getDataBaseName()
    {
        return dbConfigurationElement.getAttribute("dbname");
    }

    public static String getLeetcodeUrl()
    {
        if(leetcodeElement != null)
        {
            return leetcodeElement.getAttribute("url");
        }
        return defaultValue;
    }

    public static String getCodeChefUrl()
    {
        if(codechefElement != null)
        {
            return codechefElement.getAttribute("url");
        }
        return defaultValue;
    }

    public static String getCodeForcesUrl()
    {
        if(codeforcesElement != null) {
            return codeforcesElement.getAttribute("url");
        }
        return defaultValue;
    }

    public static String getGeeksForGeeksUrl()
    {
        if(geeksforgeeksElement != null) {
            return geeksforgeeksElement.getAttribute("url");
        }
        return defaultValue;
    }

    public static String getLeetcodeColumnName()
    {
        if(leetcodeElement != null) {
            return leetcodeElement.getAttribute("column_name");
        }
        return defaultValue;
    }

    public static String getCodeChefColumnName()
    {
        if(codechefElement != null) {
            return codechefElement.getAttribute("column_name");
        }
        return defaultValue;
    }

    public static String getCodeForcesColumnName()
    {
        if(codeforcesElement != null) {
            return codeforcesElement.getAttribute("column_name");
        }
        return defaultValue;
    }

    public static String getGeeksForGeeksColumnName()
    {
        if(geeksforgeeksElement != null) {
            return geeksforgeeksElement.getAttribute("column_name");
        }
        return defaultValue;
    }

    private static void parseInputTag(){
        NodeList list = document.getElementsByTagName("InputFilePath");
        if(list.getLength() == 0) {
            MessageHelper.showMessage(Environment.class , "Environment.InputTagMissing");
        }
        Node nodeData = list.item(0);
        if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
            inputElement = (Element) nodeData;
        }
    }

    private static void parseDataBaseConfigurationTag()
    {
        NodeList list = document.getElementsByTagName("dbconfiguration");
        if(list.getLength() == 0) {
            MessageHelper.showMessage(Environment.class , "Environment.DataBaseTagMissing");
        }
        Node nodeData = list.item(0);
        if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
            dbConfigurationElement = (Element) nodeData;
        }
    }

    private static void parseLeetcodeTag()
    {
        NodeList list = document.getElementsByTagName("leetcode");
        if(list.getLength() != 0) {
            Node nodeData = list.item(0);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
                leetcodeElement = (Element) nodeData;
            }
        }
    }

    private static void parseCodeForcesTag()
    {
        NodeList list = document.getElementsByTagName("codeforces");
        if(list.getLength() != 0) {
            Node nodeData = list.item(0);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
                codeforcesElement = (Element) nodeData;
            }
        }
    }

    private static void parseCodeChefTag()
    {
        NodeList list = document.getElementsByTagName("codechef");
        if(list.getLength() != 0)
        {
            Node nodeData = list.item(0);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
                codechefElement = (Element) nodeData;
            }
        }
    }

    private static void parseGeeksForGeeksTag()
    {
        NodeList list = document.getElementsByTagName("geeksforgeeks");
        if(list.getLength() != 0)
        {
            Node nodeData = list.item(0);
            if(nodeData.getNodeType() == Node.ELEMENT_NODE) {
                geeksforgeeksElement = (Element) nodeData;
            }
        }
    }
}
