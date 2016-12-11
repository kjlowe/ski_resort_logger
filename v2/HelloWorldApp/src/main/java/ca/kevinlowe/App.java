package ca.kevinlowe;

import org.influxdb.InfluxDBFactory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Logger log = LoggerFactory.getLogger(App.class);

    /**
     * List of all supported ski resorts
     */
    public static List<Resort> resorts = new ArrayList<Resort>();

    /**
     * Application entry point
     * @param args There are no arguments at this time
     */
    public static void main( String[] args )
    {
        log.info("Application started.");

        // Connect to influxDB
        Common.influxDB = InfluxDBFactory.connect("http://beta.kevinlowe.ca:8086", "root", "root");

        // Add resorts to the list.
        resorts.add(new ResortMountWashington());
        resorts.add(new ResortWhistlerBlackcomb());
        resorts.add(new ResortSquawAlpine());

        // Iterate through resorts
        for (Resort resort : resorts) {
            try {
                // Update lift statuses
                resort.ReadLiftStatusHistory(7);
                resort.ParseLiftStatuses();
                resort.LiftsToConsole();

                // Publish to InfluxDB
                resort.PublishNewLiftStatuses();
            }
            catch (Exception e) {
                log.error("Exception: " + e.getLocalizedMessage() + e.getMessage());
            }
        }
    }
}
