package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawingAndStoringExample extends JPanel 
    implements MouseListener, MouseMotionListener
{
    private int index;
    private int[] x;
    private int[] y;

    public DrawingAndStoringExample()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        x = new int[10];
        y = new int[10];
        index = 0;
        storeEvent(e);
    }

    public void mouseReleased(MouseEvent e)
    {
        storeEvent(e);
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
        storeEvent(e);
    }

    private void storeEvent(MouseEvent e)
    {
        if(index == x.length)
        {
            int[] tmpX = new int[2 * x.length];
            int[] tmpY = new int[2 * y.length];
            for(int i = 0; i < x.length; i++)
            {
                tmpX[i] = x[i];
                tmpY[i] = y[i];
            }
            x = tmpX;
            y = tmpY;
        }
        x[index] = e.getX();
        y[index] = e.getY();
        index++;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(x != null)
        {
            g.drawPolyline(x, y, index);
        }
    }

    public static void main(String argv[])
    {
        JFrame f = new JFrame("Zeichnen mit Speichern");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        DrawingAndStoringExample z = new DrawingAndStoringExample();
        f.add(z);
        f.setLocation(100, 100);
        f.setSize(420, 400);
        f.setVisible(true);
    }
}
