package fileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by gaetan on 01/04/16.
 */
public class FileReader {
    private File file;

    public FileReader(String filePath){
            file = new File(filePath);
    }

    public ArrayList<String[]> getData(){
        ArrayList<String[]> res = new ArrayList<String[]>();
        try{
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader lecteur = new InputStreamReader(fis);
            BufferedReader buff = new BufferedReader(lecteur);
            String ligne = "";
            buff.readLine();
            while((ligne=buff.readLine())!=null){
                String[] ligneFrag = ligne.split(";");
                res.add(ligneFrag);
            }
            buff.close();
        }catch (Exception e){
            System.out.println("invalid file");
        }

        return res;
    }

}
