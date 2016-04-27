
package metaheuristiques;

import site.Agence;
import solution.Solution;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gaetan on 01/04/16.
 */
public class RecuitSimuleLieuxFormation extends Heuristique {

    public RecuitSimuleLieuxFormation(){
        super(true);
    }

    /**
     * renvoi un tableau de booleen correspondant au centre de formation et vérifiant qu'il y ai assez de centres
     * @param centresToPut Array de boolean
     * @return Array de booléen avec minimum nbCentresMin true;
     */
    public Boolean[] getVoisins(Boolean[] centresToPut){
        Random rand = new Random();
        int alea2;
        int alea3;
        Boolean[] centresToPutTemp;
        do {
            centresToPutTemp = centresToPut.clone();
            int choixRand = rand.nextInt(3);
            switch(choixRand) {
                case 0 :
                    //1 chances sur 3 de d'enlever un centre
                    do {
                        alea2 = rand.nextInt(centresToPutTemp.length);
                    } while (!centresToPutTemp[alea2]);
                    centresToPutTemp[alea2] = !centresToPutTemp[alea2]; //on enlève ou ajoute aléatoirement un centre de la liste
                    break;
                case 1 :
                    //une chance sur 3 de remplacer un centre
                    do {
                        alea2 = rand.nextInt(centresToPutTemp.length);
                        alea3 = rand.nextInt(centresToPutTemp.length);
                    } while (centresToPutTemp[alea2] == centresToPutTemp[alea3]);
                    centresToPutTemp[alea2] = !centresToPutTemp[alea2]; //on remplace aléatoirement un centre centre de la liste
                    centresToPutTemp[alea3] = !centresToPutTemp[alea3];
                    break;
                case 2 :
                    //1 chances sur 3 de d'ajouter un centre
                    do {
                        alea2 = rand.nextInt(centresToPutTemp.length);
                    } while (centresToPutTemp[alea2]);
                    centresToPutTemp[alea2] = !centresToPutTemp[alea2]; //on enlève ou ajoute aléatoirement un centre de la liste
                    break;
            }
        } while(!isCorrect(centresToPutTemp));

        return centresToPutTemp;
    }

    /**
     * Verifie qu'il y a au moins nbCentresMin true dans un tableau de booleen.
     * @param centresToPut Boolean[]
     * @return Boolean
     */
    public Boolean isCorrect(Boolean[] centresToPut){
        int x = 0;
        for(Boolean b : centresToPut)
            if (b)
                x++;
        return x>=this.nbCentresMin();
    }

    public Solution run(){
        Random rand = new Random();

        // Génération de la solution initiale
        Boolean[] centresToPutXi = new Boolean[this.getCentres().size()];
        for(int m=0;m<this.getCentres().size(); m++)
            centresToPutXi[m] = false;
        ArrayList<Integer> listCentres = new ArrayList<Integer>();
        do {
            for (int i =0; i<this.nbCentresMin() * 1.5; i++) {
                int alea;
                do {
                    alea = rand.nextInt(this.getCentres().size());
                }while(listCentres.contains(alea));
                listCentres.add(alea);
                centresToPutXi[alea] = true;
            }
        }while(!isCorrect(centresToPutXi));
        GreedyDispAgence dispAgence = new GreedyDispAgence();
        Solution xmin = dispAgence.findSolution(listCentres);
        //RecuitSimuleDispAgence dispAgence = new RecuitSimuleDispAgence();
        //Solution xmin = dispAgence.findSolution(listCentres, 1);               //solution minimal
        Solution xi = xmin;                                                 //solution courrante à chaque itérations
        Solution xy;                                                        //solution du voisin
        Boolean[] centresToPutXy;

        // Début de l'algorithme du recuit
        int n1 = 500;
        int n2 = 500;
        Double temperature = 10.;
        for(int i = 0; i<n1; i++){
            for(int j = 0; j<n2; j++){
                //selection d'un nouveau voisin et calcul de son résultat
                listCentres = new ArrayList<>();
                centresToPutXy = this.getVoisins(centresToPutXi);
                for(int m =0; m<centresToPutXy.length; m++)
                    if (centresToPutXy[m])
                        listCentres.add(m);
                //xy = dispAgence.findSolution(listCentres, i*n2+j+1);
                xy = dispAgence.findSolution(listCentres);

                //decision de si on le prend ou non comme meilleur solution
                if(xy.getResultat()<xi.getResultat()) {
                    xi = xy;
                    centresToPutXi = centresToPutXy;
                }

                else{
                    Double p = rand.nextDouble();
                    if(p <= Math.exp(0-(xy.getResultat()-xi.getResultat())/temperature)){
                        xi=xy;
                        centresToPutXi = centresToPutXy;
                        System.out.println("on prend malgrés tout");
                    }
                }

                //mise a jour de la meilleur solution si le voisin est le minimum
                if(xmin.getResultat()>xy.getResultat())
                    xmin = xy;

                this.saveStat(xmin);
                this.printAvancement(i*n2+j,n1*n2);

                /*System.out.println("Distance : " + xy.getDistance());
                System.out.println("Centres : " + xy.getNbCentres());
                System.out.println("Prix : " + xy.getResultat());
                System.out.println("Gardé : ");
                System.out.println("** Distance : " + xmin.getDistance());
                System.out.println("** Centres : " + xmin.getNbCentres());
                System.out.println("** Prix : " +xmin.getResultat());*/
            }

            //décroissance de la température
            temperature = temperature / 2;
        }
        this.endHeuristique();
        return xmin;
    }
}