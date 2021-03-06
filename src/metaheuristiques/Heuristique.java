package metaheuristiques;

import fileHandler.FileReader;
import site.Agence;
import site.CentreFormation;
import solution.Solution;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaetan on 01/04/16.
 */
public abstract class Heuristique {
    private ArrayList<Agence> agences;
    private ArrayList<CentreFormation> centres;
    private fileHandler.FileWriter logs = null;

    public Heuristique(Boolean makeLogfile){
        agences = new ArrayList<>();
        centres = new ArrayList<>();
        FileReader fileAgences = new FileReader("ressources/ListeAgences_500.txt");
        FileReader fileCentres = new FileReader("ressources/LieuxPossibles.txt");
        ArrayList<String[]> AgencesBrute = fileAgences.getData();
        ArrayList<String[]> CentresBrute = fileCentres.getData();
        int i =0;
        int j = 0;
        for(String[] agencesFromStrings : AgencesBrute){
            i=(-1);
            do {
                i++;
            }while(agences.size() != i && agences.get(i).getNbEmploye() <= Integer.parseInt(agencesFromStrings[5]));
            Agence agTemp = new Agence(agencesFromStrings[1],
                    Double.parseDouble(agencesFromStrings[4]),
                    Double.parseDouble(agencesFromStrings[3]),
                    Integer.parseInt(agencesFromStrings[5]),
                    0);
            agences.add(i, agTemp);
        }
        i=0;
        for(Agence a1 : agences){
            a1.setId(i);
            i++;
        }
        for(String[] centresFromStrings : CentresBrute){
            CentreFormation ceTemp = new CentreFormation(centresFromStrings[1],
                    Double.parseDouble(centresFromStrings[4]),
                    Double.parseDouble(centresFromStrings[3]),
                    j);
            j++;
            centres.add(ceTemp);
        }

        //Création d'un fichier pour sauvegarder les logs
        if(makeLogfile){
            String fileName = "Logs-"+ Date.from(Instant.now()).getTime() + ".csv";
            logs = new fileHandler.FileWriter(fileName);
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

    public boolean checkCentre(HashMap<Agence, CentreFormation> map, CentreFormation ce)
    {
    	int compteur=0;
        for(Agence ag : this.getAgences())
        {
            CentreFormation ceAg = map.get(ag);
            if(ceAg != null && ceAg == ce) 
            {
                compteur += ag.getNbEmploye();

                if (compteur > 60) {
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

    public void saveStat(Solution s){
        logs.write(s);
    }

    public void endHeuristique(){
        if(logs != null)
            logs.closeFile();
    }

    public void printAvancement(int i, int n){
        int pourcent = ((i+1)*100/n);
        System.out.print("|");
        for(int j = 0; j<pourcent; j++)
            System.out.print("=");
        System.out.print(">");
        for(int k = pourcent; k<100; k++)
            System.out.print(" ");
        System.out.print("| " + (i+1) + "/" + n + " | " + pourcent + "%");
        System.out.println();
    }
}
