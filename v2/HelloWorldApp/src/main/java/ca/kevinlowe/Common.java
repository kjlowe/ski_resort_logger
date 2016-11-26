package ca.kevinlowe;

import org.apache.commons.io.IOUtils;
import org.influxdb.InfluxDB;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class Common {

    /**
     * Universal connecion to InfluxDB.
     */
    public static InfluxDB influxDB;

    /**
     * Fetches the contents of a webpage
     * @param urlString URL of the webpage
     * @return the webpage HTML
     */
     public static String HTTPGetContents(String urlString) {
         try {
             URL url = new URL(urlString);
             URLConnection con = url.openConnection();
             InputStream in = con.getInputStream();
             String encoding = con.getContentEncoding();
             encoding = encoding == null ? "UTF-8" : encoding;
             return IOUtils.toString(in, encoding);
         }
         catch(Exception e)
         {
            return "";
         }
    }
}
