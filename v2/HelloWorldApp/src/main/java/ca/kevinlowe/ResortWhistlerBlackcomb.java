package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortWhistlerBlackcomb extends Resort {

    public void UpdateLifts() {

        // Get HTML from Whistler Blackcomb website.
        String html = Common.HTTPGetContents("https://www.whistlerblackcomb.com/mountain-info/snow-report#lifts-and-trails");
        html = StringEscapeUtils.unescapeHtml4(html);

        // Matching pattern for lifts and statuses
        Pattern pattern = Pattern.compile("<span></span>(.+?)</td>[\n\r ]+<td>([A-Z]+)</td>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {

            // Convert status to enumeration
            LiftStatus status = LiftStatus.ERROR;
            switch (matcher.group(2)) {
                case "CLOSED":
                    status = LiftStatus.CLOSED;
                    break;
                case "OPEN":
                    status = LiftStatus.OPEN;
                    break;
                case "STANDBY":
                    status = LiftStatus.STANDBY;
            }

            liftStatusMap.put(matcher.group(1), status);
        }
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

