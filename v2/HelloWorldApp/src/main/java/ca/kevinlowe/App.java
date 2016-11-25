package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Resort resort = new ResortMountWashington();
        resort.UpdateLifts();

        for (Map.Entry<String,LiftStatus> entry : resort.liftStatusMap.entrySet())
        {
            System.out.println(entry.getKey() + " > " + entry.getValue());
        }
        System.out.println();
        System.out.println("Number of Lifts : " + resort.getLiftCount());
    }
}
