package ca.kevinlowe;

import java.util.*;

/**
 * Class representing a lift and it status history.
 */
public class Lift {

    /**
     * name of the lift.
     */
    public String name;

    /**
     * current status of the lift.
     */
    public LiftStatus statusCurrent;

    /**
     * loaded history of the parsedLiftStatuses status changes
     */
    public LinkedList<LiftStatusChange> statusHistory = new LinkedList<LiftStatusChange>();
}
