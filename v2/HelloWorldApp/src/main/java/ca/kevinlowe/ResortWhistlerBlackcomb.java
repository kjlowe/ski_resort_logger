package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortWhistlerBlackcomb extends Resort {

    public ResortWhistlerBlackcomb() {
        liftTextToTag.put("CREEKSIDE GONDOLA", "creekside");
        liftTextToTag.put("SHOWCASE T-BAR", "showcase");
        liftTextToTag.put("HARMONY 6 EXPRESS", "harmony");
        liftTextToTag.put("FRANZ'S CHAIR", "franz");
        liftTextToTag.put("PEAK EXPRESS", "peak");
        liftTextToTag.put("SYMPHONY EXPRESS", "symphony");
        liftTextToTag.put("OLYMPIC CHAIR", "olympic");
        liftTextToTag.put("CATSKINNER CHAIR", "catskinner");
        liftTextToTag.put("CRYSTAL RIDGE EXPRESS", "crystal");
        liftTextToTag.put("PEAK 2 PEAK GONDOLA", "peak2peak");
        liftTextToTag.put("7TH HEAVEN EXPRESS", "7th-heaven");
        liftTextToTag.put("FITZSIMMONS EXPRESS", "fitzsimmons");
        liftTextToTag.put("MAGIC CHAIR", "magic");
        liftTextToTag.put("WHISTLER VILLAGE GONDOLA", "village-gondola");
        liftTextToTag.put("BIG RED EXPRESS", "big-red");
        liftTextToTag.put("EXCALIBUR GONDOLA", "excalibur");
        liftTextToTag.put("SOLAR COASTER EXPRESS", "solar-coaster");
        liftTextToTag.put("HORSTMAN T-BAR", "horstman");
        liftTextToTag.put("T-BARS", "tbars");
        liftTextToTag.put("EXCELERATOR EXPRESS", "excelerator");
        liftTextToTag.put("GLACIER EXPRESS", "glacier");
        liftTextToTag.put("JERSEY CREAM EXPRESS", "jersey-cream");
        liftTextToTag.put("COCA-COLA TUBE PARK", "tube-park");
        liftTextToTag.put("WIZARD EXPRESS", "wizard");
        liftTextToTag.put("EMERALD EXPRESS", "emerald");
        liftTextToTag.put("GARBANZO EXPRESS", "garbanzo");

        statusTextToStatus.put("CLOSED", LiftStatus.CLOSED);
        statusTextToStatus.put("STANDBY", LiftStatus.STANDBY);
        statusTextToStatus.put("OPEN", LiftStatus.OPEN);

        liftsPattern = "<span></span>(.+?)</td>[\n\r ]+<td>([A-Z]+)</td>";
        liftsURL = "https://www.whistlerblackcomb.com/mountain-info/snow-report#lifts-and-trails";

        resortTag = "whistler";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

