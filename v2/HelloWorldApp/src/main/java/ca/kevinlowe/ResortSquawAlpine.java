package ca.kevinlowe;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class ResortSquawAlpine extends Resort {

    public ResortSquawAlpine() {

        textToLiftTag.put("First Venture", "first-venture");
        textToLiftTag.put("Tucker", "tucker");
        textToLiftTag.put("Big Blue Express", "big-blue");
        textToLiftTag.put("Kangaroo", "kangaroo");
        textToLiftTag.put("SnoVentures Carpet", "sv-carpet");
        textToLiftTag.put("Red Dog", "real-dog");
        textToLiftTag.put("Headwall Express", "headwall");
        textToLiftTag.put("Squaw Creek", "squaw");
        textToLiftTag.put("Mountain Meadow", "mountain");
        textToLiftTag.put("Shuttle to Alpine Meadows", "alpine-shuttle");
        textToLiftTag.put("Wylee", "mylee");
        textToLiftTag.put("Belmont", "belmont");
        textToLiftTag.put("Shirley Express", "shirley");
        textToLiftTag.put("Sherwood Express", "sherwood");
        textToLiftTag.put("Subway", "subway");
        textToLiftTag.put("Granite Chief Lift", "granite");
        textToLiftTag.put("Boon", "boon");
        textToLiftTag.put("Silverado", "silverado");
        textToLiftTag.put("Aerial Tram", "aerial");
        textToLiftTag.put("Far East Express", "far-east");
        textToLiftTag.put("Murphy", "murphy");
        textToLiftTag.put("Squaw One Express", "squaw-one");
        textToLiftTag.put("Emigrant", "emigrent");
        textToLiftTag.put("Bailey's Beach", "baileys");
        textToLiftTag.put("Gold Coast Express", "gold-coast");
        textToLiftTag.put("Meadow", "meadow");
        textToLiftTag.put("The Pulley", "pulley");
        textToLiftTag.put("Solitude", "solitude");
        textToLiftTag.put("Lakeview", "lakeview");
        textToLiftTag.put("Alpine Bowl ", "alpine");
        textToLiftTag.put("Hot Wheels", "hot-wheels");
        textToLiftTag.put("Big Carpet", "big-carpet");
        textToLiftTag.put("Roundhouse", "roundhouse");
        textToLiftTag.put("Funitel", "funitel");
        textToLiftTag.put("Broken Arrow Lift", "broken-arrow");
        textToLiftTag.put("Exhibition", "exhibition");
        textToLiftTag.put("Summit Six", "summit-six");
        textToLiftTag.put("Shuttle to Squaw Valley", "squaw-shuttle");
        textToLiftTag.put("Yellow", "yellow");
        textToLiftTag.put("Siberia Express", "siberia");
        textToLiftTag.put("Little Carpet", "little-carpet");
        textToLiftTag.put("KT22 Express", "KT22");
        textToLiftTag.put("Olympic Lady", "olympic");
        textToLiftTag.put("Scott", "scott");

        textToLiftStatus.put("C", LiftStatus.CLOSED);
        textToLiftStatus.put("P", LiftStatus.SCHEDULED);
        textToLiftStatus.put("O", LiftStatus.OPEN);

        liftsPattern = "<div class=\"lift clearfix\"><div class=\"cell\">(.+?)</div>.+?status status-(.)";
        liftsURL = "http://squawalpine.com/skiing-riding/weather-conditions-webcams/lift-grooming-status";

        resortTag = "squaw";
    }

    public void UpdateWeather() {

    }

    public void UpdateForcast() {

    }
}

