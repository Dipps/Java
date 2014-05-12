package cg.seekarte;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cg.punkteditor.Punktliste;

public class Zeichenflaeche extends JLabel implements IMapModelListener
{

    private Punktliste points;

    // private final int circleRadius = 3;

    // private double[][] matrix;

    public Zeichenflaeche(final MapModel model)
    {
        super(model.getSelectedMapIcon());
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.TOP);

        points = model.getPoints();
        // matrix = model.getInverseMapTransform();

    }

    @Override
    public void mapModelChanged(final MapModel model)
    {
        setIcon(model.getSelectedMapIcon());
        // matrix = model.getInverseMapTransform();
    }

    @Override
    public void pointsChanged(final MapModel model)
    {
        points = model.getPoints();
        repaint();

    }

    @Override
    public void paintComponent(final Graphics g)
    {
        super.paintComponent(g);
        points.draw(g);
        // g.drawOval(50, 50, circleRadius, circleRadius);
        // int preX = 0;
        // int preY = 0;
        // int count = 0;
        // for (final double[] d : points)
        // {
        // final double[] v = Matrix.matMult(matrix, d);
        // // Werte Runden (erwarte keine negativen Werte)
        // final int x = ((int) (v[0] + 0.5));
        // final int y = ((int) (v[1] + 0.5));
        // g.fillOval(x - circleRadius, y - circleRadius, circleRadius * 2,
        // circleRadius * 2);
        //
        // if (count >= 1)
        // {
        // g.drawLine(preX, preY, x, y);
        // }
        //
        // preX = x;
        // preY = y;
        // count++;
        //
        // }

    }
}
