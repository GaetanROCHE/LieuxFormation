package metaheuristiques;

import site.Agence;
import site.CentreFormation;
import solution.Solution;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaetan on 27/04/16.
 */
public class GreedyDispAgence extends Heuristique{
    public GreedyDispAgence() {
        super(false);
    }

    public Solution findSolution(ArrayList<Integer> ids){
        HashMap<Agence,CentreFormation> solutionMap = new HashMap<>();
        HashMap<Integer,Integer> centreEmploye = new HashMap<>();
        CentreFormation ce = null;
        Double distanceTemp;
        for(Agence a : this.getAgences()){
            Double min = Double.MAX_VALUE;
            for(Integer idCe : ids){
                distanceTemp = this.getCentres().get(idCe).distance(a);
                if(distanceTemp<min && (centreEmploye.get(idCe)== null || centreEmploye.get(idCe)+a.getNbEmploye()<=60)) {
                    min = distanceTemp;
                    ce = this.getCentres().get(idCe);
                }
            }
            if (ce != null) {
                solutionMap.put(a, ce);
                if (centreEmploye.get(ce.getId()) != null)
                    centreEmploye.put(ce.getId(),centreEmploye.get(ce.getId()) + a.getNbEmploye());
                else
                    centreEmploye.put(ce.getId(), a.getNbEmploye());
            }
            else{
                System.out.println("erreur greedy");
            }
        }
        return new Solution(solutionMap);
    }
}
