
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
        Boolean[] agencesToPut = new Boolean[this.getAgences().size()];
        ArrayList<Integer> listCentres = new ArrayList<Integer>();
        for(Agence a : this.getAgences()) {
            Boolean alea = rand.nextBoolean();
            agencesToPut[a.getId()] = alea;
            if(alea)
                listCentres.add(a.getId());
        }
        RecuitSimuleDispAgence dispAgence = new RecuitSimuleDispAgence();
        Solution xmin = dispAgence.FindSolution(listCentres); //solution minimal
        Solution xi = xmin;                                   //solution courrante à chaque itérations
        Solution xy;                                          //solution du voisin

        // Début de l'algorithme du recuit
        int n1 = 200;
        int n2 = 100;
        int i = 0;
        int temperature = 20;
        for(int j = 0; i<n1; i++){
            for(int k = 0; j<n2; j++){
                //selection d'un nouveau voisin et calcul de son résultat
                int alea2 = rand.nextInt(this.getAgences().size());
                agencesToPut[alea2] = !agencesToPut[alea2]; //on enlève ou ajoute aléatoirement un centre de la liste
                for(int m =0; m<agencesToPut.length; m++)
                    if (agencesToPut[m])
                        listCentres.add(m);
                xy = dispAgence.FindSolution(listCentres);

                //decision de si on le prend ou non comme meilleur solution
                if(xy.getResultat()<xi.getResultat())
                    xi=xy;
                else{
                    int p = rand.nextInt();
                    if(p <= xi.getResultat()-xy.getResultat()){
                        xi=xy;
                    }
                }

                //mise a jour de la meilleur solution si le voisin est le minimum
                if(xmin.getResultat()>xy.getResultat())
                    xmin = xy;
            }

            //décroissance de la température
            temperature /= 0.5;
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