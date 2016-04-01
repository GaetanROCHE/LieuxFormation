package metaheuristiques;

import lieuxformation.Agence;
import solution.Solution;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gaetan on 01/04/16.
 */
public class RecuitSimuleLieuxFormation extends Heuristique {

    public RecuitSimuleLieuxFormation(){
        super();
    }

    public Solution run(){
        Random rand = new Random();

        // Génération de la solution initiale
        ArrayList<Agence> agencesToPut = this.getAgences();
        int nbCentresMin = nbCentresMin();
        ArrayList<Integer> listCentres = new ArrayList<Integer>();
        for(int l=0; l<nbCentresMin; l++)
            listCentres.add(agencesToPut.remove(rand.nextInt(agencesToPut.size())).getId());
        Solution xmin = RecuitSimuleDispAgence.FindSolution(listCentres);

        // Début de l'algorithme du cours
        int n1 = 1;
        int n2 = 2;
        int i = 0;
        int temperature;
        for(int j = 0; i<n1; i++){
            for(int k = 0; j<n2; j++){

            }
        }
        return null;
    }

    public int nbCentresMin(){
        int res = 0;
        for(Agence a : this.getAgences())
            res += a.getNbEmploye();
        return res/60+1;
    }
}
