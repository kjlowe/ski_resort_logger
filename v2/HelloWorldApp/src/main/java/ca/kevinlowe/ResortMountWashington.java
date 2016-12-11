package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortMountWashington extends Resort {

    public ResortMountWashington() {
        textToLiftTag.put("Upper Carpet", "upper-carpet");
        textToLiftTag.put("Lower Carpet", "lower-carpet");
        textToLiftTag.put("Whiskey Jack", "whiskey-jack");
        textToLiftTag.put("The Tube Park", "tube-park");
        textToLiftTag.put("Teaching Carpet", "teach-carpet");
        textToLiftTag.put("Sunrise", "sunrise");
        textToLiftTag.put("Boomerang", "boomerang");
        textToLiftTag.put("Eagle", "eagle");
        textToLiftTag.put("Hawk", "hawk");
        textToLiftTag.put("Middle Carpet", "middle-carpet");

        textToLiftStatus.put("statusclosed", LiftStatus.CLOSED);
        textToLiftStatus.put("statusopen", LiftStatus.OPEN);
        textToLiftStatus.put("statusscheduled", LiftStatus.SCHEDULED);

        liftsPattern = "<h2 class=\"liftstatus\">(.+?)</h2>.+?<span class=\"([a-z]+?)\">";
        liftsURL = "https://www.mountwashington.ca/weather/live-lift-status.html";

        resortTag = "washington";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

