package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class PaintExample1 extends JPanel
{
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.RED);
        g.drawLine(30, 200, 200, 200);
        g.drawLine(200, 200, 200, 100);
        g.drawLine(200, 100, 150, 50);
        g.drawLine(150, 50, 100, 100);
        g.drawLine(100, 100, 100, 200);
        g.drawLine(100, 200, 200, 100);
        g.drawLine(200, 100, 100, 100);
        g.drawLine(100, 100, 200, 200);
        g.drawLine(200, 200, 270, 200);
    }

    public static void main(String args[])
    {
        JFrame f = new JFrame("Nikolaus-Haus");
        PaintExample1 niko = new PaintExample1();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(niko);
        f.setLocation(200, 200);
        f.setSize(300, 300);
        f.setVisible(true);
    }
}
