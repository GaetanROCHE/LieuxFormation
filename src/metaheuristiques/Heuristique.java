package metaheuristiques;

import fileHandler.FileReader;
import site.Agence;
import site.CentreFormation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaetan on 01/04/16.
 */
public abstract class Heuristique {
    private ArrayList<Agence> agences;
    private ArrayList<CentreFormation> centres;

    public Heuristique(){
        agences = new ArrayList<Agence>();
        centres = new ArrayList<CentreFormation>();
        FileReader fileAgences = new FileReader("ressources/ListeAgences_500.txt");
        FileReader fileCentres = new FileReader("ressources/LieuxPossibles.txt");
        ArrayList<String[]> AgencesBrute = fileAgences.getData();
        ArrayList<String[]> CentresBrute = fileCentres.getData();
        int i =0;
        int j = 0;
        for(String[] agencesFromStrings : AgencesBrute){
            Agence agTemp = new Agence(agencesFromStrings[1],
                    Double.parseDouble(agencesFromStrings[4]),
                    Double.parseDouble(agencesFromStrings[3]),
                    Integer.parseInt(agencesFromStrings[5]),
                    i);
            i++;
            agences.add(agTemp);
        }
        for(String[] centresFromStrings : CentresBrute){
            CentreFormation ceTemp = new CentreFormation(centresFromStrings[1],
                    Double.parseDouble(centresFromStrings[4]),
                    Double.parseDouble(centresFromStrings[3]),
                    j);
            j++;
            centres.add(ceTemp);
        }
        System.out.println("Heuristique chargé");
        System.out.println("Nombre minimum de centres : " + this.nbCentresMin());
    }

    public boolean checkMap(HashMap<Agence, CentreFormation> map)
    {
        HashMap<CentreFormation, Integer> mapCentres = new HashMap();
        for(Agence ag : this.getAgences())
        {
            CentreFormation ce = map.get(ag);
            if(ce != null) {
                if (mapCentres.get(ce) == null)
                    mapCentres.put(ce, 0);
                mapCentres.put(ce, ag.getNbEmploye() + mapCentres.get(ce));

                if (mapCentres.get(ce) > 60) {
                    return false;
                }
            }
        }
        return true;// solution valide
    }

    public ArrayList<CentreFormation> getCentres(){
        return centres;
    }

    public ArrayList<Agence> getAgences(){
        return agences;
    }

    public int nbCentresMin(){
        int res = 0;
        for(Agence a : this.getAgences())
            res += a.getNbEmploye();
        return res/60+1;
    }

}
