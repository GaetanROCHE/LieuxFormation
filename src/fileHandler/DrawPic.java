package fileHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

/**
 * Created by Aceitix on 27/04/2016.
 */
public class DrawPic {
    public DrawPic(){

    }

    public void drawSolution(){
        JFrame frame = new JFrame("Demo load image");
        frame.getContentPane().add(new UserInterface.FenetreSolution());
        frame.pack();
        frame.setVisible(true);
    }
}
