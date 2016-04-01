package solution;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaetan on 01/04/16.
 */
public class Solution {
    private HashMap<Integer, Integer> solution;
    private Double resultat = 0.;

    public Solution(HashMap<Integer, Integer> soluc){
        solution = soluc;
    }

    public double getResultat(){
        if(resultat == 0.){
            return 0.;
        }
        else
            return resultat;
    }

    public ArrayList<Solution> getVoisins(){
        ArrayList<Solution> res = new ArrayList<Solution>();
        return res;
    }
}
