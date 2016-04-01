package fileHandler;

import java.io.File;
import java.lang.reflect.Array;

/**
 * Created by gaetan on 01/04/16.
 */
public class FileReader {
    private File file;

    public FileReader(String filePath){
        try {
            file = new File(filePath);
        }catch (Exception e){
            System.out.println("invalid file");
        }
    }

    public Array getData(){
        return null;
    }

}
