package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

import static src.Main.m;

/** This heuristic tries to minimize both distance and number of drivers. We will start from heuristic 3 and add the
 * ponderation of the number of possible drivers that are passangers.
 *
 * We shall ponderate here more the distance than the drivers
 */

//WORKS WELL WITH HILL CLIMBING

public class Heuristic4 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {
        Map map = (Map) n;
        ArrayList<Pair> e = map.getEstatConductors();
        int total = 0;


        //WE TRY TO MINIMIZE ALL THE DISTANCES
        //We panalize for the distance since we wanna minimize it, but we penalize it much more if we exceed it.
        int dist;
        for (Pair a : e)
        {
            dist = (Integer) ((Pair) a.getFirst()).getFirst();
            if (dist > 300) total += (dist-300)*400;  //We panalize the exceed of the distance.

            total += min(300,dist);
        }

        //WE PANALIZE THE NON PICKED UP PASSANGERS
        ArrayList<Boolean> b = map.getEstaRecullit();
        for (Boolean r : b)
            if (!r)
                total += 850;  //we wanna make sure everyone has been picked up


        //WE TRY TO MINIMIZE THE NUMBER OF DRIVERS
        int p = m-e.size(); //number of drivers that are passangers
        total -= 1400*p;

        return total;
    }

    private int min (int a, int b)
    {
        if (a < b) return a;
        return b;
    }
}
