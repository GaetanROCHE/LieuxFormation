package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aceitix on 27/04/2016.
 */
public class FenetreSolution extends JPanel {
    Image carte = null;
    public void UserInterface(){
        try {
            carte= ImageIO.read(new File("carte-de-france-vierge.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        g.drawImage(carte, 0, 0, null);
    }
}
