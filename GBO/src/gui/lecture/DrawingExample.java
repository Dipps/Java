package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawingExample extends JPanel implements MouseListener,
                                          MouseMotionListener
{
    private int lastX, lastY;

    public DrawingExample()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void mouseEntered(MouseEvent e)
    {
        info("mouseEntered", e);
    }

    public void mouseExited(MouseEvent e)
    {
        info("mouseExited", e);
    }

    public void mousePressed(MouseEvent e)
    {
        info("mousePressed", e);
        lastX = e.getX();
        lastY = e.getY();
    }

    public void mouseReleased(MouseEvent e)
    {
        info("mouseReleased", e);
        draw(e.getX(), e.getY());
    }

    public void mouseClicked(MouseEvent e)
    {
        info("mouseClicked", e);
    }

    public void mouseMoved(MouseEvent e)
    {
        info("mouseMoved", e);
    }

    public void mouseDragged(MouseEvent e)
    {
        info("mouseDragged", e);
        draw(e.getX(), e.getY());
    }

    private void info(String s, MouseEvent e)
    {
        System.out.println(s + " at " + e.getPoint());
    }

    private void draw(int x, int y)
    {
        Graphics g = getGraphics();
        g.drawLine(lastX, lastY, x, y);
        lastX = x;
        lastY = y;
    }

    public static void main(String argv[])
    {
        JFrame f = new JFrame("Zeichnen");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        DrawingExample z = new DrawingExample();
        f.add(z);
        f.setLocation(100, 100);
        f.setSize(420, 400);
        f.setVisible(true);
    }
}
