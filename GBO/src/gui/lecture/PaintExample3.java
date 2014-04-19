package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class PaintExample3 extends JPanel
{
    private int i;

    public void paintComponent(Graphics g)
    {
        System.out.print("X");
        i++;
        if(i == 80)
        {
            System.out.println();
            i = 0;
        }

        super.paintComponent(g);
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
        PaintExample3 niko = new PaintExample3();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(niko);
        f.setLocation(200, 200);
        f.setSize(300, 300);
        f.setVisible(true);
    }
}
