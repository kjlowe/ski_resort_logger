package ca.kevinlowe;

import java.util.*;

/**
 * Created by kevinlowe on 2016-11-24.
 */
public class Lift {

    public String name;
    public LiftStatus statusCurrent;
    public LinkedList<LiftStatusChange> statusHistory;
}
