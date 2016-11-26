package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The resort class managing the parsing, loading and storing of resort data.
 * @author kevinlowe
 */
public abstract class Resort {

    /**
     * URL of the resorts live lift status page.
     */
    public String liftsURL;

    /**
     * InfluxDB tag of this resort.
     */
    public String resortTag;

    /**
     * Pattern to parse out the lift and their statuses from HTML page.
     */
    public String liftsPattern;

    /**
     * Maps to convert from parsed strings to lift-tags nad statuses
     */
    public Map<String, String> textToLiftTag = new HashMap<String,String>();
    public Map<String, LiftStatus> textToLiftStatus = new HashMap<String,LiftStatus>();

    /**
     * Pattern to parse out the lift and their statuses
     */
    public Map<String, LiftStatus> lifts = new HashMap<String, LiftStatus>();

    /**
     * @return The number of lifts prased from the webpage.
     */
    public int getLiftCount() {
        return lifts.size();
    }

    /**
     * Reads the resorts live lift status page and parses out the current status of each lift.
     * Lifts on the page much match the lifts configured in the resorts JSON file.
     * Currently error are written to the console.
     */
    public void UpdateLifts() {

        // Get live lift status HTML from the resort website.
        String html = Common.HTTPGetContents(liftsURL);
        html = StringEscapeUtils.unescapeHtml4(html);

        // Matching pattern for lifts and statuses
        Pattern pattern = Pattern.compile(liftsPattern, Pattern.DOTALL);
        Matcher liftMatcher = pattern.matcher(html);

        while (liftMatcher.find()) {

            // Use found lift name to lookup lift influxDB tag
            if (!textToLiftTag.containsKey(liftMatcher.group(1))) {
                System.out.println("Unknown Lift: " + liftMatcher.group(1));
                continue;
            }
            String lift = textToLiftTag.get(liftMatcher.group(1));

            // Convert found status to enumeration
            if (!textToLiftStatus.containsKey(liftMatcher.group(2))) {
                System.out.println("Unknown Status: " + liftMatcher.group(2));
                continue;
            }
            LiftStatus status = textToLiftStatus.get(liftMatcher.group(2));

            lifts.put(lift, status);
        }
    }

    /**
     * Prints out the current status of each lift to the console.
     */
    public void LiftsToConsole() {
        System.out.println(getClass().toString());
        for (Map.Entry<String, LiftStatus> entry : lifts.entrySet()) {
            System.out.println(entry.getKey() + " > " + entry.getValue());
        }
        System.out.println("Number of Lifts : " + getLiftCount());
        System.out.println();
    }

    /**
     * Publishes lift data to the influxDB server.
     */
    public void PublishLiftData() {

        BatchPoints batchPoints = BatchPoints.database("resorts").build();

        // Add a point for each lift.
        for (Map.Entry<String, LiftStatus> entry : lifts.entrySet()) {
            Point point = Point.measurement("lifts").tag("resort",resortTag)
                    .tag("lift",entry.getKey()).addField("status", entry.getValue().toString()).build();

            batchPoints.point(point);
        }
        Common.influxDB.write(batchPoints);
    }
}
