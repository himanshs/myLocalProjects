package amdocs.dbcompare.loader;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader
{

    public PropertiesLoader()
    {
    }

    public static synchronized PropertiesLoader getInstance()
    {
        if(m_propertiesLoader == null)
        {
            Map map = System.getenv();
            String s = "";
            if(map.containsKey("amdocs.epc") || map.get("amdocs.epc") != null)
            {
                System.out.println((new StringBuilder()).append("Property File Path -> ").append(map.get("amdocs.epc")).toString());
                s = (String)map.get("amdocs.epc");
            } else
            if(System.getProperty("amdocs.epc") != null && System.getProperty("amdocs.epc") != "")
                s = System.getProperty("amdocs.epc");
            else
                System.out.println("Cannot find application.config file!!!");
            m_propertiesLoader = new PropertiesLoader();
            properties = new Properties();
            try
            {
                properties.load(new FileInputStream((new StringBuilder()).append(System.getProperty("amdocs.epc"))
                		.append(File.separator).append("public")
                		.append(File.separator).append("properties")
                		.append(File.separator).append("application.config").toString()));
            }
            catch(IOException ioexception)
            {
                System.out.println("Error reading the application.config file.");
                ioexception.printStackTrace();
            }
        }
        return m_propertiesLoader;
    }

    public String getPropertyValue(String s)
    {
        return properties.getProperty(s);
    }

    public static synchronized void resetInstance()
    {
        m_propertiesLoader = null;
    }

    private static PropertiesLoader m_propertiesLoader;
    private static Properties properties;
}