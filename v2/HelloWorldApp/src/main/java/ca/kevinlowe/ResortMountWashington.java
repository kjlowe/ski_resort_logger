package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortMountWashington extends Resort {

    public void UpdateLifts() {

        // Get HTML from Whistler Blackcomb website.
        String html = Common.HTTPGetContents("https://www.mountwashington.ca/weather/live-lift-status.html");
        html = StringEscapeUtils.unescapeHtml4(html);

        // Matching pattern for lifts and statuses
        Pattern pattern = Pattern.compile("<h2 class=\"liftstatus\">(.+?)</h2>.+?<span class=\"([a-z]+?)\">");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {

            String lift = matcher.group(1);
            String statusText = matcher.group(2);

            // Convert status to enumeration
            LiftStatus status = LiftStatus.ERROR;
            switch (statusText) {
                case "statusclosed":
                    status = LiftStatus.CLOSED;
                    break;
                case "statusopen":
                    status = LiftStatus.OPEN;
                    break;
                case "statusstandby":
                    status = LiftStatus.STANDBY;
            }

            lifts.put(lift, status);
        }
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

