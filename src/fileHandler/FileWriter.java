package fileHandler;

import solution.Solution;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Aceitix on 25/04/2016.
 */
public class FileWriter {

    private BufferedWriter buff;

    public FileWriter(String filePath){
        System.out.println("Creation du fichier : " + filePath);
        File file = new File(filePath);
        try{
            FileOutputStream fis = new FileOutputStream(file);
            OutputStreamWriter lecteur = new OutputStreamWriter(fis);
            buff = new BufferedWriter(lecteur);
        }catch (Exception e){
            System.out.println("error creating file");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String[]> write(Solution s){
        ArrayList<String[]> res = new ArrayList<String[]>();
        try{
            String ligne = "" + s.getNbCentres() + ";" + s.getDistance() + ";" + s.getResultat() + "; \n";
            buff.write(ligne);
        }catch (Exception e){
            System.out.println("error during writting");
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void closeFile(){
        try {
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
