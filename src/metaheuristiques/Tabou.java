package metaheuristiques;

import lieuxformation.Agence;
import lieuxformation.CentreFormation;
import solution.Solution;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aceitix on 21/04/2016.
 */
public class Tabou extends Heuristique {
    public Solution run(){
        ArrayList<Solution> tabou = new ArrayList<>();
        HashMap<Agence, CentreFormation> h = new HashMap<>();//à généré aléatiorement

        int n = 200;
        for(int i = 0 ; i<n; i++){

        }
        Solution s = new Solution(h);
        return s;
    }
}
