
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

    /**
     * renvoi un tableau de booleen correspondant au centre de formation et vérifiant qu'il y ai assez de centres
     * @param agencesToPut Array de boolean
     * @return Array de booléen avec minimum nbCentresMin true;
     */
    public Boolean[] getVoisins(Boolean[] agencesToPut){
        Random rand = new Random();
        do {
            int alea2 = rand.nextInt(agencesToPut.length);
            agencesToPut[alea2] = !agencesToPut[alea2]; //on enlève ou ajoute aléatoirement un centre de la liste
        } while(!isCorrect(agencesToPut));
        return agencesToPut;
    }

    /**
     * Verifie qu'il y a au moins nbCentresMin true dans un tableau de booleen.
     * @param agencesToPut Boolean[]
     * @return Boolean
     */
    public Boolean isCorrect(Boolean[] agencesToPut){
        int x = 0;
        for(Boolean b : agencesToPut)
            if (b)
                x++;
        return x>=this.nbCentresMin();
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
        Solution xmin = dispAgence.findSolution(listCentres);               //solution minimal
        Solution xi = xmin;                                                 //solution courrante à chaque itérations
        Solution xy;                                                        //solution du voisin

        // Début de l'algorithme du recuit
        int n1 = 10;
        int n2 = 10;
        int temperature = 20000;
        for(int i = 0; i<n1; i++){
            System.out.println("*** Iteration centres : " + i);
            for(int j = 0; j<n2; j++){
                //selection d'un nouveau voisin et calcul de son résultat
                agencesToPut = this.getVoisins(agencesToPut);
                for(int m =0; m<agencesToPut.length; m++)
                    if (agencesToPut[m])
                        listCentres.add(m);
                xy = dispAgence.findSolution(listCentres);

                //decision de si on le prend ou non comme meilleur solution
                if(xy.getResultat()<xi.getResultat())
                    xi=xy;
                else{
                    int p = rand.nextInt();
                    if(p <= (xi.getResultat()-xy.getResultat())/temperature){
                        xi=xy;
                    }
                }

                //mise a jour de la meilleur solution si le voisin est le minimum
                if(xmin.getResultat()>xy.getResultat())
                    xmin = xy;
            }

            //décroissance de la température
            temperature /= 1.5;
        }
        return xmin;
    }

    public int nbCentresMin(){
        int res = 0;
        for(Agence a : this.getAgences())
            res += a.getNbEmploye();
        return res/60+1;
    }
}