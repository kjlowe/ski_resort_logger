package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortSquawAlpine extends Resort {

    public void UpdateLifts() {

        // Get HTML from resort website.
        String html = Common.HTTPGetContents("http://squawalpine.com/skiing-riding/weather-conditions-webcams/lift-grooming-status");
        html = StringEscapeUtils.unescapeHtml4(html);

        // Matching pattern for lifts and statuses
        Pattern pattern = Pattern.compile("<div class=\"lift clearfix\"><div class=\"cell\">(.+?)</div>.+?status status-(.)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {


            // Convert status to enumeration
            LiftStatus status = LiftStatus.ERROR;
            switch (matcher.group(2)) {
                case "C":
                    status = LiftStatus.CLOSED;
                    break;
                case "P":
                    status = LiftStatus.SCHEDULED;
                    break;
                case "O":
                    status = LiftStatus.OPEN;
            }

            lifts.put(matcher.group(1), status);
        }
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

