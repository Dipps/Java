package cg.seekarte;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cg.matrix.Matrix;

public class Zeichenflaeche extends JLabel implements IMapModelListener {

    private ArrayList<double[]> points;
    private final int circleRadius = 3;
    private double[][] matrix;

    public Zeichenflaeche(MapModel model) {
        super(model.getSelectedMapIcon());
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.TOP);

        points = new ArrayList<>();
        matrix = model.getInverseMapTransform();

    }

    @Override
    public void mapModelChanged(MapModel model) {
        setIcon(model.getSelectedMapIcon());
        matrix = model.getInverseMapTransform();
    }

    @Override
    public void pointsChanged(MapModel model) {
        points = model.getPoints();
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawOval(50, 50, circleRadius, circleRadius);
        int preX = 0;
        int preY = 0;
        int count = 0;
        for (double[] d : points) {
            double[] v = Matrix.matMult(matrix, d);
            // Werte Runden (erwarte keine negativen Werte)
            int x = ((int) (v[0] + 0.5));
            int y = ((int) (v[1] + 0.5));
            g.fillOval(x - circleRadius, y - circleRadius, circleRadius * 2,
                    circleRadius * 2);

            if (count >= 1) {
                g.drawLine(preX, preY, x, y);
            }

            preX = x;
            preY = y;
            count++;

        }

    }
}
