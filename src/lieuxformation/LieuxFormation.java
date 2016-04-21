/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lieuxformation;

import metaheuristiques.RecuitSimuleLieuxFormation;
import solution.Solution;

/**
 *
 * @author gaetan
 */
public class LieuxFormation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RecuitSimuleLieuxFormation heuristique = new RecuitSimuleLieuxFormation();
        Solution s = heuristique.run();
        System.out.println("nombres de centres : " + s.getNbCentres());
        System.out.println("kilomètres parcourus : " + s.getDistance());
        System.out.println("resultat : " + s.getResultat());
    }
    
}
