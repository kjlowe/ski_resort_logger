package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortSquawAlpine extends Resort {

    public ResortSquawAlpine() {
        liftTextToTag.put("First Venture", "first-venture");
        liftTextToTag.put("Tucker", "tucker");
        liftTextToTag.put("Big Blue Express", "big-blue");
        liftTextToTag.put("Kangaroo", "kangaroo");
        liftTextToTag.put("SnoVentures Carpet", "sv-carpet");
        liftTextToTag.put("Red Dog", "real-dog");
        liftTextToTag.put("Headwall Express", "headwall");
        liftTextToTag.put("Squaw Creek", "squaw");
        liftTextToTag.put("Mountain Meadow", "mountain");
        liftTextToTag.put("Shuttle to Alpine Meadows", "alpine-shuttle");
        liftTextToTag.put("Wylee", "mylee");
        liftTextToTag.put("Belmont", "belmont");
        liftTextToTag.put("Shirley Express", "shirley");
        liftTextToTag.put("Sherwood Express", "sherwood");
        liftTextToTag.put("Subway", "subway");
        liftTextToTag.put("Granite Chief Lift", "granite");
        liftTextToTag.put("Boon", "boon");
        liftTextToTag.put("Silverado", "silverado");
        liftTextToTag.put("Aerial Tram", "aerial");
        liftTextToTag.put("Far East Express", "far-east");
        liftTextToTag.put("Murphy", "murphy");
        liftTextToTag.put("Squaw One Express", "squaw-one");
        liftTextToTag.put("Emigrant", "emigrent");
        liftTextToTag.put("Bailey's Beach", "baileys");
        liftTextToTag.put("Gold Coast Express", "gold-coast");
        liftTextToTag.put("Meadow", "meadow");
        liftTextToTag.put("The Pulley", "pulley");
        liftTextToTag.put("Solitude", "solitude");
        liftTextToTag.put("Lakeview", "lakeview");
        liftTextToTag.put("Alpine Bowl ", "alpine");
        liftTextToTag.put("Hot Wheels", "hot-wheels");
        liftTextToTag.put("Big Carpet", "big-carpet");
        liftTextToTag.put("Roundhouse", "roundhouse");
        liftTextToTag.put("Funitel", "funitel");
        liftTextToTag.put("Broken Arrow Lift", "broken-arrow");
        liftTextToTag.put("Exhibition", "exhibition");
        liftTextToTag.put("Summit Six", "summit-six");
        liftTextToTag.put("Shuttle to Squaw Valley", "squaw-shuttle");
        liftTextToTag.put("Yellow", "yellow");
        liftTextToTag.put("Siberia Express", "siberia");
        liftTextToTag.put("Little Carpet", "little-carpet");
        liftTextToTag.put("KT22 Express", "KT22");
        liftTextToTag.put("Olympic Lady", "olympic");
        liftTextToTag.put("Scott", "scott");

        statusTextToStatus.put("C", LiftStatus.CLOSED);
        statusTextToStatus.put("P", LiftStatus.SCHEDULED);
        statusTextToStatus.put("O", LiftStatus.OPEN);

        liftsPattern = "<div class=\"lift clearfix\">.+?<div class=\"cell\">(.+?)</div>.+?status status-(.)";
        liftsURL = "http://squawalpine.com/skiing-riding/weather-conditions-webcams/lift-grooming-status";

        resortTag = "squaw";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

