package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortMountWashington extends Resort {

    public ResortMountWashington() {
        liftTextToTag.put("Upper Carpet", "upper-carpet");
        liftTextToTag.put("Lower Carpet", "lower-carpet");
        liftTextToTag.put("Whiskey Jack", "whiskey-jack");
        liftTextToTag.put("The Tube Park", "tube-park");
        liftTextToTag.put("Teaching Carpet", "teach-carpet");
        liftTextToTag.put("Sunrise", "sunrise");
        liftTextToTag.put("Boomerang", "boomerang");
        liftTextToTag.put("Eagle", "eagle");
        liftTextToTag.put("Hawk", "hawk");
        liftTextToTag.put("Middle Carpet", "middle-carpet");

        liftTextToIgnore.add("Hawk Cam");

        statusTextToStatus.put("statusclosed", LiftStatus.CLOSED);
        statusTextToStatus.put("statusopen", LiftStatus.OPEN);
        statusTextToStatus.put("statusscheduled", LiftStatus.SCHEDULED);
        statusTextToStatus.put("statusstandby", LiftStatus.STANDBY);

        liftsPattern = "<h2 class=\"liftstatus\">(.+?)</h2>.+?<span class=\"([a-z]+?)\">";
        liftsURL = "https://www.mountwashington.ca/weather/live-lift-status.html";

        resortTag = "washington";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

