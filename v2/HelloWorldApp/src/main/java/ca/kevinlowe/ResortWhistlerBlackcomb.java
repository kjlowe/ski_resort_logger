package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortWhistlerBlackcomb extends Resort {

    public ResortWhistlerBlackcomb() {
        textToLiftTag.put("CREEKSIDE GONDOLA", "creekside");
        textToLiftTag.put("SHOWCASE T-BAR", "showcase");
        textToLiftTag.put("HARMONY 6 EXPRESS", "harmony");
        textToLiftTag.put("FRANZ'S CHAIR", "franz");
        textToLiftTag.put("PEAK EXPRESS", "peak");
        textToLiftTag.put("SYMPHONY EXPRESS", "symphony");
        textToLiftTag.put("OLYMPIC CHAIR", "olympic");
        textToLiftTag.put("CATSKINNER CHAIR", "catskinner");
        textToLiftTag.put("CRYSTAL RIDGE EXPRESS", "crystal");
        textToLiftTag.put("PEAK 2 PEAK GONDOLA", "peak2peak");
        textToLiftTag.put("7TH HEAVEN EXPRESS", "7th-heaven");
        textToLiftTag.put("FITZSIMMONS EXPRESS", "fitzsimmons");
        textToLiftTag.put("MAGIC CHAIR", "magic");
        textToLiftTag.put("WHISTLER VILLAGE GONDOLA", "village-gondola");
        textToLiftTag.put("BIG RED EXPRESS", "big-red");
        textToLiftTag.put("EXCALIBUR GONDOLA", "excalibur");
        textToLiftTag.put("SOLAR COASTER EXPRESS", "solar-coaster");
        textToLiftTag.put("HORSTMAN T-BAR", "horstman");
        textToLiftTag.put("T-BARS", "tbars");
        textToLiftTag.put("EXCELERATOR EXPRESS", "excelerator");
        textToLiftTag.put("GLACIER EXPRESS", "glacier");
        textToLiftTag.put("JERSEY CREAM EXPRESS", "jersey-cream");
        textToLiftTag.put("COCA-COLA TUBE PARK", "tube-park");
        textToLiftTag.put("WIZARD EXPRESS", "wizard");
        textToLiftTag.put("EMERALD EXPRESS", "emerald");
        textToLiftTag.put("GARBANZO EXPRESS", "garbanzo");

        textToLiftStatus.put("CLOSED", LiftStatus.CLOSED);
        textToLiftStatus.put("STANDBY", LiftStatus.STANDBY);
        textToLiftStatus.put("OPEN", LiftStatus.OPEN);

        liftsPattern = "<span></span>(.+?)</td>[\n\r ]+<td>([A-Z]+)</td>";
        liftsURL = "https://www.whistlerblackcomb.com/mountain-info/snow-report#lifts-and-trails";

        resortTag = "whistler";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

