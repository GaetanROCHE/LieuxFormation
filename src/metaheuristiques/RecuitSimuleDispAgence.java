package metaheuristiques;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Random;
        import static java.lang.StrictMath.exp;

        import lieuxformation.Agence;
        import lieuxformation.CentreFormation;
        import solution.Solution;


/**
 * Created by gaetan on 01/04/16.
 */
public class RecuitSimuleDispAgence extends Heuristique{

    public RecuitSimuleDispAgence(){
        super();
    }

    public Solution voisinage(Solution solution1)
    {
        HashMap<Integer, Integer> map = solution1.getSolution();
        Random rand = new Random();
        int min = 0;
        int max = map.size();
        int nombreAleatoire1 = rand.nextInt(max - min + 1) + min;
        rand = new Random();
        int nombreAleatoire2 = rand.nextInt(max - min + 1) + min;
        int idAgRandom = this.getAgences().get(nombreAleatoire1).getId();//on choisit une agence aléatoire
        int idCeRandom = this.getCentres().get(nombreAleatoire2).getId();//un centre aléatoire

        if(map.get(idAgRandom) == idCeRandom)
        {
            do{
                rand = new Random();
                nombreAleatoire2 = rand.nextInt(max - min + 1) + min;
                idCeRandom = this.getCentres().get(nombreAleatoire2).getId();//un centre aléatoire
            }while(map.get(idAgRandom) == idCeRandom);
        }
        map.put(idAgRandom, idCeRandom);//on modifier la map: une agence est reliée à un centre différent
        Solution retour = new Solution(map);
        return retour;
    }

    public double avancementTemperature(double temperatureInitiale){

        double nouvelleTemperature;
        nouvelleTemperature = temperatureInitiale/(1.5);
        return nouvelleTemperature;
    }

    public Solution findSolution(ArrayList<Integer> Identifiants){//Recherche d'une solution pour les centres proposés
        HashMap<Integer, Integer> map = new HashMap();
        Solution solutionInitiale = new Solution(map);

        for(Agence ag : this.getAgences()){//pour chaque agence
            double min = Double.MAX_VALUE;
            CentreFormation meilleurCentre = null;
            for (int id : Identifiants)    //on parcours chaque centre
            {
                CentreFormation ce = this.getCentres().get(id);
                double distance = ag.distance(ce);//on calcule la distance
                if(distance<min)// si c'est la distance min, on conserve la distance et le centre en question
                {
                    min = distance;
                    meilleurCentre = ce;
                }
            }
            map.put(ag.getId(), meilleurCentre.getId());// à la fin on ajoute à la map, la ville avec le centre le plus proche
        }

        double temperatureInitiale = 1000;

        Solution solutionMin = solutionInitiale;
        double resultatmin =  solutionMin.getResultat();
        int i = 0;
        int n1 = 1;
        int n2 = 2;
        Solution solutionEnCours = solutionInitiale;
        Solution solutionSuivante=solutionInitiale;
        double temperatureEnCours = temperatureInitiale;
        double temperatureSuivante = temperatureInitiale;
        for(int k=0; k<n1; k++)
        {

            if(temperatureSuivante!=temperatureInitiale){
                temperatureEnCours = temperatureSuivante;
            }
            for(int l=0; l<n2; l++)
            {

                if(solutionSuivante!=solutionInitiale){
                    solutionEnCours = solutionSuivante;
                }


                //VOISINAGE : CHANGEMENT D'AFFECTATION D'UNE AGENCE  A UN CENTRE
                Solution y = voisinage(solutionEnCours);
                double deltaf = y.getResultat() - solutionEnCours.getResultat();
                if(deltaf<=0){
                    solutionSuivante = y;
                    if(solutionSuivante.getResultat()<resultatmin)
                    {
                        resultatmin=solutionSuivante.getResultat();
                        solutionMin=solutionSuivante;
                    }
                }
                else{
                        Random rand = new Random();
                        int min = 0;
                        int max = 1;
                        int p = rand.nextInt(max - min + 1) + min;
                        if(p<= (exp(deltaf/temperatureEnCours)) )
                        {
                            solutionSuivante = y;
                        }
                        else
                        {
                            solutionSuivante = solutionEnCours;
                        }
                    }
                i= i+1;
            }
            temperatureSuivante=avancementTemperature(temperatureEnCours);
        }
        return solutionMin;

    }





}
