package metaheuristiques;

import fileHandler.FileReader;
import lieuxformation.Agence;
import lieuxformation.CentreFormation;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gaetan on 01/04/16.
 */
public abstract class Heuristique {
    private ArrayList<Agence> agences;
    private ArrayList<CentreFormation> centres;

    public Heuristique(){
        agences = new ArrayList<Agence>();
        centres = new ArrayList<CentreFormation>();
        FileReader fileAgences = new FileReader("ressources/ListeAgences_100.txt");
        FileReader fileCentres = new FileReader("ressources/LieuxPossibles.txt");
        ArrayList<String[]> AgencesBrute = fileAgences.getData();
        ArrayList<String[]> CentresBrute = fileCentres.getData();
        for(String[] agencesFromStrings : AgencesBrute){;
            Agence agTemp = new Agence(agencesFromStrings[1],
                    Double.parseDouble(agencesFromStrings[4]),
                    Double.parseDouble(agencesFromStrings[3]),
                    Integer.parseInt(agencesFromStrings[5]));
            agences.add(agTemp);
        }
        for(String[] centresFromStrings : CentresBrute){;
            CentreFormation ceTemp = new CentreFormation(centresFromStrings[1],
                    Double.parseDouble(centresFromStrings[4]),
                    Double.parseDouble(centresFromStrings[3]));
            centres.add(ceTemp);
        }
    }

    public ArrayList<CentreFormation> getCentres(){
        return centres;
    }

    public ArrayList<Agence> getAgences(){
        return agences;
    }


}
