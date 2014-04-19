package gui.lecture;

import javax.swing.*;

public class DrawingAndStoringApplet extends JApplet
{
    public void init()
    {
        //System.out.println("huhu");
        DrawingAndStoringExample z = new DrawingAndStoringExample();
        add(z);

        /*
        JFrame f = new JFrame("Bitte Passwort eingeben");
        JTextField tf = new JTextField();
        f.add(tf);
        f.setSize(400, 100);
        f.setVisible(true);
        */
    }
}
