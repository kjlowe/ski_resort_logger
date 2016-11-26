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
        Common.influxDB = InfluxDBFactory.connect("http://beta.kevinlowe.ca:8086", "root", "root");

        // Add resorts to the list.
        resorts.add(new ResortMountWashington());
        resorts.add(new ResortWhistlerBlackcomb());
        resorts.add(new ResortSquawAlpine());

        // Iterate through resorts
        // Update lift statuses
        // Print statuses
        for (Resort resort : resorts) {
            try {
                resort.UpdateLifts();
                resort.LiftsToConsole();
                resort.PublishLiftData();
            }
            catch (Exception e) {}
        }
    }
}
