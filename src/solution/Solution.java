package solution;

import lieuxformation.Agence;
import lieuxformation.CentreFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaetan on 01/04/16.
 */
public class Solution {
    private HashMap<CentreFormation, Agence> solution; //couple centre - agences
    private Double resultat = 0.;

    public Solution(HashMap<CentreFormation, Agence> soluc){
        solution = soluc;
    }

    /**
     * @return Double calcul la valeur de la solution si elle n'est pas déjà calculée et la retourne;
     */
    public double getResultat(){
        if(resultat == 0.)
            for(Map.Entry<CentreFormation, Agence> e : solution.entrySet())
                resultat += e.getKey().distance(e.getValue());
        return resultat;
    }

    /**
     * @return HashMap La solution courrante
     */
    public HashMap<CentreFormation, Agence> getSolution() {
        if (!this.solution.isEmpty()) {
            return solution;
        } else return null;
    }
}
