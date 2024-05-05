package AutomationCore.src.automation_helper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ResourceBundleHelper {

    private static Map<String , String> resourceStrings = null;

    public static String getString(Object object , String key)
    {
        if(resourceStrings == null || !resourceStrings.containsKey(key)){
            fillTheResourceMapWithBundle(object);
        }
        return resourceStrings.get(key);
    }

    private static void fillTheResourceMapWithBundle(Object object) {
        String bundleName = getTheRequiredBundleName(object);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName);
        Enumeration<String> keys = resourceBundle.getKeys();
        if(resourceStrings == null)
        {
            resourceStrings = new HashMap<>();
        }
        while(keys.hasMoreElements())
        {
            String key = keys.nextElement();
            String value = resourceBundle.getString(key);
            resourceStrings.put(key , value);
        }
    }

    private static String getTheRequiredBundleName(Object object) {
        String className = ((Class<?>) object).getSimpleName();
        if(className.contains("Automation")){
            return "AutomationCore/src/automation_helper/XPath";
        }
        return "StringProperties";
    }

}
