package ca.kevinlowe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public abstract class Resort {

    Map<String, LiftStatus> lifts = new HashMap<String, LiftStatus>();

    public int getLiftCount() {
        return lifts.size();
    }

    public abstract void UpdateLifts();

    public abstract void UpdateWeather();

    public abstract void UpdateForcast();

}
