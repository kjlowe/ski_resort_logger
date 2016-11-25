package ca.kevinlowe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static List<Resort> resorts = new ArrayList<>();

    public static void main( String[] args )
    {
        resorts.add(new ResortMountWashington());
        resorts.add(new ResortWhistlerBlackcomb());
        resorts.add(new ResortSquawAlpine());

        for (Resort resort : resorts) {
            try {
                resort.UpdateLifts();

                System.out.println(resort.getClass().toString());
                for (Map.Entry<String, LiftStatus> entry : resort.lifts.entrySet()) {
                    System.out.println(entry.getKey() + " > " + entry.getValue());
                }
                System.out.println("Number of Lifts : " + resort.getLiftCount());
                System.out.println();
            }
            catch (Exception e) {}
        }


    }
}
