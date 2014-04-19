package gui.lecture;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class RobotExample3
{
    public static void main(String[] args)
    {
        int rc = JOptionPane.showConfirmDialog(null,
                "Soll der Bildschirm jetzt aufgenommen werden?",
                "Frage", JOptionPane.YES_NO_OPTION);
        if(rc != 0) //means: not yes
        {
            return;
        }

        Robot robot = null;
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            System.err.println(e);
            return;
        }
        robot.delay(500);
        
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = new Rectangle(0, 0, screensize.width,
                                       screensize.height);
        BufferedImage img = robot.createScreenCapture(rect);
        try
        {
            ImageIO.write(img, "png", new File("./screenshot.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}