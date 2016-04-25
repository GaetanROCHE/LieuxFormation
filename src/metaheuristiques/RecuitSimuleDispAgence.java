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
        do{
            Random rand = new Random();
            int min = 0;
            int max = map.size()-1;
            int nombreAleatoire1 = rand.nextInt(max - min + 1) + min;
            rand = new Random();
            int max2 = ids.size()-1;
            int nombreAleatoire2 = rand.nextInt(max2 - min + 1) + min;
            Agence AgRandom = this.getAgences().get(nombreAleatoire1);//on choisit une agence al�atoire
            CentreFormation CeRandom = this.getCentres().get(ids.get(nombreAleatoire2));//un centre al�atoire

            if(map.get(AgRandom) == CeRandom)
            {
                do{
                    rand = new Random();
                    nombreAleatoire2 = rand.nextInt(max2 - min + 1) + min;
                    CeRandom = this.getCentres().get(ids.get(nombreAleatoire2));//un centre al�atoire
                }while(map.get(AgRandom) == CeRandom);
            }
            map.put(AgRandom, CeRandom);//on modifier la map: une agence est reli�e � un centre diff�rent
            retour = new Solution(map);}
        while(!checkSolution(retour));
        return retour;
    }

    public double avancementTemperature(double temperatureInitiale){

        double nouvelleTemperature;
        nouvelleTemperature = temperatureInitiale/(1.5);
        return nouvelleTemperature;
    }

    public Solution findSolution(ArrayList<Integer> Identifiants){//Recherche d'une solution pour les centres propos�s
        HashMap<Agence, CentreFormation> map = new HashMap();
        Solution solutionInitiale = new Solution(map);

        /*for(Agence ag : this.getAgences()){//pour chaque agence
            do{
                double min = Double.MAX_VALUE;
                CentreFormation meilleurCentre = null;
                for (int id : Identifiants)    //on parcours chaque centre
                {
                    CentreFormation ce = this.getCentres().get(id);
                    double distance = ag.distance(ce);//on calcule la distance


                    if(distance<min && check == true)// si c'est la distance min, on conserve la distance et le centre en question
                    {
                        min = distance;
                        meilleurCentre = ce;
                    }
                }
                map.put(ag, meilleurCentre);// a la fin on ajoute a la map, la ville avec le centre le plus proche
            }
            while (!checkMap(map));
        }*/
        int z = 0;
        for(Agence ag : this.getAgences()) {//pour chaque agence on attribue un centre al�atoire;
            //tem.out.println("agence : " + z);
            z++;
            do {
                Random rand = new Random();
                int min = 0;
                int max = Identifiants.size() - 1;
                int nombreAleatoire1 = rand.nextInt(max - min + 1) + min;
                map.put(ag, this.getCentres().get(Identifiants.get(nombreAleatoire1)));
            }
            while(!checkMap(map));
        }

        double temperatureInitiale = 1;

        Solution solutionMin = solutionInitiale;
        double resultatmin =  solutionMin.getResultat();
        int i = 0;
        int n1 = 20;
        int n2 = 20;
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
                Solution y = voisinage(solutionEnCours,Identifiants);
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
                    if(p<= (Math.exp(-deltaf/temperatureEnCours)) )
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
