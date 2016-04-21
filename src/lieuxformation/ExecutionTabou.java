package lieuxformation;

import metaheuristiques.Tabou;
import solution.Solution;

/**
 * Created by gaetan on 21/04/16.
 */
public class ExecutionTabou {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tabou heuristique = new Tabou();
        Solution s = heuristique.run();
        System.out.println("nombres de centres : " + s.getNbCentres());
        System.out.println("kilomètres parcourus : " + s.getDistance());
        System.out.println("resultat : " + s.getResultat());
    }
}
