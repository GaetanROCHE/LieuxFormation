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

    /**
     * @return Double calcul la valeur de la solution si elle n'est pas déjà calculée et la retourne;
     */
    public double getResultat(){
        if(resultat == 0.){
            return 0.;
        }
        else
            return resultat;
    }

    /**
     * @return HashMap La solution courrante
     */
    public HashMap<Integer, Integer> getSolution() {
        if (!this.solution.isEmpty()) {
            return solution;
        } else return null;
    }

    /**
     * @return ArrayList<Solution> La liste des voisins de la solution
     */
    public ArrayList<Solution> getVoisins(){
        ArrayList<Solution> res = new ArrayList<Solution>();
        return res;
    }
}
