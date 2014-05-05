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

import cg.matrix.Matrix;

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
        // g.drawLine(10, 10, x, y);
        // g.setColor(kreisFarbe);
        // g.fillOval(x - r, y - r, 2 * r, 2 * r);
        // g.setColor(Color.BLACK);
        // g.drawOval(x - r, y - r, 2 * r, 2 * r);

        final double[] P0 =
        { 100.0, 100.0 };
        final double[] P1 =
        { 400.0, 400.0 };
        final double[] T =
        { x - getWidth() * 0.5, y - getHeight() * 0.5 };
        final double[][] randbed =
        { P0, P1, T, };

        final int samples = 4;
        final double dt = 1.0 / samples;
        double[] c_alt = P0;
        for (int i = 0; i <= 4; i++)
        {
            final double t = (i + 1) / (double) (samples + 1);
            final double[] c = kurve(t, randbed);
            g.drawLine((int) c_alt[0], (int) c_alt[1], (int) c[0], (int) c[1]);
            c_alt = c;

        }
        g.drawLine((int) c_alt[0], (int) c_alt[1], (int) P1[0], (int) P1[1]);

    }

    private double[] kurve(final double t, final double[][] randbedingung)
    {
        return Matrix.matMult(new double[][]
        { bindefunktionen(t) }, randbedingung)[0];
    }

    private double[] bindefunktionen(final double t)
    {
        return new double[]
        { -t * t + 1, t * t, -t * t + t };
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
