package metaheuristiques;

import site.Agence;
import site.CentreFormation;
import solution.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Heuristique tabou avec comme ensemble de voisinage les solutions différents de seulement une affectation d'agence vers un centre.
 */
public class Tabou extends Heuristique {


    public Solution run(){
        ArrayList<Solution> tabou = new ArrayList<>();
        HashMap<Agence, CentreFormation> h = this.getAlea();//à généré aléatiorement
        Solution solutionActu = new Solution(h);
        Solution bestSolution = solutionActu;

        int n = 200;
        for(int i = 0 ; i<n; i++){
            System.out.println("Solution courrante : ");
            System.out.println("nombres de centres : " + solutionActu.getNbCentres());
            System.out.println("kilomètres parcourus : " + solutionActu.getDistance());
            System.out.println("resultat : " + solutionActu.getResultat());
            Solution bestVoisin = new Solution(null);
            Double min = Double.MAX_VALUE;
            for(Solution s : this.getVoisins(solutionActu, tabou))
                if(s.getResultat()<min)
                    bestVoisin = s;

            if(!(bestVoisin.getResultat()<solutionActu.getResultat()))
                tabou.add(solutionActu);
            solutionActu = bestVoisin;
            if(bestVoisin.getResultat()<bestSolution.getResultat())
                bestSolution = bestVoisin;

        }
        return bestSolution;
    }

    /**
     * renvoi l'ensemble des voisins pour une solution données.
     * Les voisins diffèrent d'une affectation de centres.
     * @param s
     * @return
     */
    public ArrayList<Solution> getVoisins(Solution s, ArrayList<Solution> tabou){ // /!\ il faut ajouter la liste tabou
        HashMap<Agence,CentreFormation> map = s.getSolution();
        ArrayList<Solution> voisins = new ArrayList<>();
        int i = 0;
        for(Agence a : this.getAgences()) {
            for (CentreFormation c : this.getCentres()) {
                if(map.get(a) != c) {
                    HashMap<Agence, CentreFormation> mapTemp = new HashMap<>(map);
                    mapTemp.put(a, c);
                    Solution solutionTemp = new Solution(mapTemp);
                    if(!tabou.contains(solutionTemp))
                        voisins.add(solutionTemp);
                }
            }
        }
        return voisins;
    }

    public HashMap<Agence, CentreFormation> getAlea(){
        HashMap<Agence, CentreFormation> res = new HashMap<>();
        Random rand = new Random();
        for(Agence ag : this.getAgences()) {//pour chaque agence on attribue un centre al�atoire;
            do {
                res.put(ag, this.getCentres().get(rand.nextInt(this.getCentres().size())));
            }
            while(!checkMap(res));
        }
        return res;
    }
}
