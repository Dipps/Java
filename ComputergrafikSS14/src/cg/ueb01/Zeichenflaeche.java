package cg.ueb01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Zeichenflaeche extends JLabel implements MouseMotionListener, MouseListener
{

    private int x = 300;

    private int y = 300;

    private final int r = 50;

    private Color kreisFarbe = Color.RED;

    public Zeichenflaeche()
    {
        super(new ImageIcon("res/erde.jpg"));
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.TOP);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(final Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f));
        g.drawLine(10, 10, x, y);
        g.setColor(kreisFarbe);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);

    }

    @Override
    public void mouseDragged(final MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        repaint();

    }

    public Color getKreisFarbe()
    {
        return kreisFarbe;
    }

    public void setKreisFarbe(final Color kreisFarbe)
    {
        this.kreisFarbe = kreisFarbe;
        repaint();
    }

    @Override
    public void mouseMoved(final MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }
}
