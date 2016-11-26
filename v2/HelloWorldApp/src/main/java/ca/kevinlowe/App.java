package ca.kevinlowe;

import org.influxdb.InfluxDBFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static List<Resort> resorts = new ArrayList<>();

    public static void main( String[] args )
    {
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
                resort.UpdateLifts();
                resort.LiftsToConsole();

                // Publish to InfluxDB
                resort.PublishLiftData();
            }
            catch (Exception e) {}
        }
    }
}
