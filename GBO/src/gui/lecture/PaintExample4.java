package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class PaintExample4 extends JPanel
{
    private int i = 0;
    private boolean inc = true;

    public void paintComponent(Graphics g)
    {
        if(inc)
        {
            i++;
            if(i == 100)
            {
                inc = false;
            }
        }
        else
        {
            i--;
            if(i == 0)
            {
                inc = true;
            }
        }

        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(30 + i, 200 + i, 200 + i, 200 + i);
        g.drawLine(200 + i, 200 + i, 200 + i, 100 + i);
        g.drawLine(200 + i, 100 + i, 150 + i, 50 + i);
        g.drawLine(150 + i, 50 + i, 100 + i, 100 + i);
        g.drawLine(100 + i, 100 + i, 100 + i, 200 + i);
        g.drawLine(100 + i, 200 + i, 200 + i, 100 + i);
        g.drawLine(200 + i, 100 + i, 100 + i, 100 + i);
        g.drawLine(100 + i, 100 + i, 200 + i, 200 + i);
        g.drawLine(200 + i, 200 + i, 270 + i, 200 + i);
    }

    public static void main(String args[])
    {
        JFrame f = new JFrame("Nikolaus-Haus");
        PaintExample4 niko = new PaintExample4();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(niko);
        f.setLocation(20, 20);
        f.setSize(400, 400);
        f.setVisible(true);
    }
}
