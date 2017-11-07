package top.itning.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/***
 *
 * @author : ning
 * @version : 1.0.0
 * @date :   2017/11/7
**/
public class PropertiesUtil {
    private final static Properties PROPERTIES = new Properties();

    private PropertiesUtil() {
    }

    public static String getValueByKey(String key, String url) {
        if (key != null) {
            try {
                PROPERTIES.load(new InputStreamReader(new FileInputStream(url),"utf-8"));
            } catch (IOException e) {
                System.err.println("url error");
                e.printStackTrace();
            }
            return PROPERTIES.getProperty(key);
        }
        return null;
    }
}