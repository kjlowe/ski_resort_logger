package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, Lift> lifts = new HashMap<String, Lift>();

    /**
     * @return The number of parsedLiftStatuses prased from the webpage.
     */
    public int getLiftCount() {
        return lifts.size();
    }

    /**
     * Get a lift from the lift list. If the lift does not exist it is created.
     * @param liftTag The tag of the lift.
     * @return The lift object.
     */
    public Lift getLift(String liftTag) {
        if (!lifts.containsKey(liftTag)) {
            Lift lift = new Lift();
            lift.name = liftTag;
            lifts.put(liftTag, lift);
        }
        return lifts.get(liftTag);
    }

    /**
     * Reads the resorts live lift status page and parses out the current status of each lift.
     * Currently parsedLiftStatuses are configured in a child class.
     * Currently errors are written to the console.
     */
    public void ParseLiftStatuses() {

        // Get live lift status HTML from the resort website.
        String html = Common.HTTPGetContents(liftsURL);
        html = StringEscapeUtils.unescapeHtml4(html);

        // Matching pattern for parsedLiftStatuses and statuses
        Pattern pattern = Pattern.compile(liftsPattern, Pattern.DOTALL);
        Matcher liftMatcher = pattern.matcher(html);

        while (liftMatcher.find()) {

            // Use found lift name to lookup lift influxDB tag
            if (!textToLiftTag.containsKey(liftMatcher.group(1))) {
                System.out.println("Unknown Lift: " + liftMatcher.group(1));
                continue;
            }
            String liftText = textToLiftTag.get(liftMatcher.group(1));

            // Convert found status to enumeration
            if (!textToLiftStatus.containsKey(liftMatcher.group(2))) {
                System.out.println("Unknown Status: " + liftMatcher.group(2));
                continue;
            }
            LiftStatus status = textToLiftStatus.get(liftMatcher.group(2));

            // Set the current status of the lift
            getLift(liftText).statusCurrent = status;
        }
    }

    /**
     * Prints out the current status of each lift to the console.
     */
    public void LiftsToConsole() {
        System.out.println(getClass().toString());
        for (Map.Entry<String, Lift> entry : lifts.entrySet()) {
            System.out.println(entry.getKey() + " > " +
                    entry.getValue().statusCurrent + " > " +
                    "History: " + entry.getValue().statusHistory.size());
        }
        System.out.println("Number of Lifts : " + getLiftCount());
    }

    /**
     * Publishes lift data to the influxDB server.
     */
    public void PublishNewLiftStatuses() {

        BatchPoints batchPoints = BatchPoints.database("resorts").build();

        // Add a point for each lift.
        for (Map.Entry<String, Lift> entry : lifts.entrySet()) {

            Lift lift = entry.getValue();

            if (lift.statusHistory.size() == 0 ||
                    lift.statusHistory.getLast().status != lift.statusCurrent) {

                Point point = Point.measurement("lifts").tag("resort", resortTag)
                        .tag("lift", lift.name).addField("status", lift.statusCurrent.toString()).build();
                batchPoints.point(point);
            }
        }

        // Run the influxDB query
        if (batchPoints.getPoints().size() > 1) {
            Common.influxDB.write(batchPoints);
            System.out.println("Wrote " + batchPoints.getPoints().size() + " points to InfluxDB!");
        }
    }

    /**
     * Read the resort history from the last X number od days.
     */
    public void ReadLiftStatusHistory(int days) {

        Query query = new Query("SELECT status, lift FROM lifts WHERE resort = '" + resortTag + "' GROUP BY lift", "resorts");
        QueryResult queryResult = Common.influxDB.query(query);

        // 1 Result object
        if (queryResult.hasError() || queryResult.getResults().size() != 1)
            return;

        QueryResult.Result result = queryResult.getResults().get(0);

        if (result.getSeries() == null)
            return;

        // Loop through each lift (series)
        for (QueryResult.Series series : result.getSeries()) {

            // Get the lift and clear it's history. We are going to fill the history.
            Lift lift = getLift(series.getTags().get("lift"));
            lift.statusHistory.clear();

            // Fill status history.
            int timeIndex = series.getColumns().indexOf("time");
            int statusIndex = series.getColumns().indexOf("status");
            for(List<Object> values : series.getValues()) {

                LiftStatusChange statusChange = new LiftStatusChange();
                statusChange.status = LiftStatus.valueOf((String)values.get(statusIndex));

                String timeRFC3339 = (String)values.get(timeIndex);
                statusChange.time = (new DateTime(timeRFC3339, DateTimeZone.UTC)).toDate();

                lift.statusHistory.add(statusChange);
            }
        }
    }
}


