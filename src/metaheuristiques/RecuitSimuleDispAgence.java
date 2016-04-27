package metaheuristiques;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Random;

        import site.Agence;
        import site.CentreFormation;
        import solution.Solution;


/**
 * Created by gaetan on 01/04/16.
 */
public class RecuitSimuleDispAgence extends Heuristique{

    public RecuitSimuleDispAgence(){
        super(false);
    }

    public boolean checkSolution(Solution solution)
    {
        HashMap<Agence, CentreFormation> map = solution.getSolution();
        return checkMap(map);
    }

    public Solution voisinage(Solution solution1,ArrayList<Integer> ids)
    {
        HashMap<Agence, CentreFormation> map = solution1.getSolution();
        Solution retour;
        Agence AgRandom;
        CentreFormation CeRandom;
        do{
            AgRandom = this.getAgences().get(new Random().nextInt(this.getAgences().size()));//on choisit une agence aleatoire
            CeRandom = this.getCentres().get(ids.get(new Random().nextInt(ids.size())));//un centre aleatoire
        }while(map.get(AgRandom) == CeRandom || !checkCentre(map, CeRandom));
        map.put(AgRandom, CeRandom);//on modifier la map: une agence est reliee a un centre different
        return new Solution(map);
    }

    public double avancementTemperature(double temperatureInitiale){
        return temperatureInitiale/2;
    }

    public Solution findSolution(ArrayList<Integer> Identifiants, int iterations){//Recherche d'une solution pour les centres proposes
        HashMap<Agence, CentreFormation> map = new HashMap();
        Solution solutionInitiale = new Solution(map);
        CentreFormation ce;
        for(Agence ag : this.getAgences()) {//pour chaque agence on attribue un centre aleatoire;
            //tem.out.println("agence : " + z);
            do {
                Random rand = new Random();
                int min = 0;
                int max = Identifiants.size() - 1;
                int nombreAleatoire1 = rand.nextInt(max - min + 1) + min;
                ce = this.getCentres().get(Identifiants.get(nombreAleatoire1));
            }
            while(!checkCentre(map, ce));
            map.put(ag, ce);
        }
        double temperatureInitiale = 20.;

        Solution solutionMin = solutionInitiale;
        int n1 = 100;
        Solution solutionEnCours;
        Solution solutionSuivante=solutionInitiale;
        double temperatureEnCours = temperatureInitiale;
        for(int k=0; k<n1; k++)
        {
            for(int l=0; l<iterations; l++)
            {
                solutionEnCours = solutionSuivante;

                //VOISINAGE : CHANGEMENT D'AFFECTATION D'UNE AGENCE  A UN CENTRE
                Solution y = voisinage(solutionEnCours,Identifiants);
                double deltaf = y.getResultat() - solutionEnCours.getResultat();
                if(deltaf<=0){
                    solutionSuivante = y;
                    if(solutionSuivante.getResultat()<solutionMin.getResultat())
                        solutionMin=solutionSuivante;
                }
                else{
                    Random rand = new Random();
                    double p = rand.nextDouble();
                    if(p<= (Math.exp(-deltaf/temperatureEnCours)) )
                        solutionSuivante = y;
                    else
                        solutionSuivante = solutionEnCours;
                }
            }
            temperatureEnCours=avancementTemperature(temperatureEnCours);
        }
        return solutionMin;

    }





}
