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
    private HashMap<Agence, CentreFormation> solution; //couple centre - agences
    private Double resultat = 0.;
    private Double distance = 0.;
    private int nbCentres = 0;

    public Solution(HashMap<Agence, CentreFormation> soluc){
        solution = soluc;
    }

    /**
     * @return Double calcul la valeur de la solution si elle n'est pas déjà calculée et la retourne;
     */
    public double getResultat(){
        if(resultat == 0.) {
            this.distance = 0.;
            ArrayList<CentreFormation> centres = new ArrayList<>();
            for (Map.Entry<Agence, CentreFormation> e : solution.entrySet()) {
                this.distance += e.getKey().distance(e.getValue()) * e.getKey().getNbEmploye();
                if(!centres.contains(e.getValue()))
                    centres.add(e.getValue());
            }
            this.nbCentres = centres.size();
            resultat = this.distance * 0.4 + 3000 * this.nbCentres;
        }
        return resultat;
    }

    public int getNbCentres() {
        if(nbCentres == 0)
            this.getResultat();
        return nbCentres;
    }

    public Double getDistance() {
        if(distance == 0.)
            this.getResultat();
        return distance;
    }

    /**
     * @return HashMap|null La solution courrante
     */
    public HashMap<Agence, CentreFormation> getSolution() {
        if (!this.solution.isEmpty()) {
            return solution;
        } else return null;
    }
}
