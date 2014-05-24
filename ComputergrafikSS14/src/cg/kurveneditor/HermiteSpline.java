package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import cg.matrix.Matrix;
import cg.punkteditor.Punktliste;

public class HermiteSpline implements IGeometrieView {

    private int anzahl;
    private double[][] randbed = new double[4][2];

    @Override
    public void draw(Graphics g, Punktliste p, Color c) {
        update(p);
        Graphics2D g2d = (Graphics2D) g;

        if (anzahl >= 4) {
            // zeiche Tangenten T0 T1
            g2d.setColor(Color.YELLOW);
            g2d.drawLine((int) randbed[0][0], (int) randbed[0][1],
                    (int) randbed[2][0], (int) randbed[2][1]);
            g2d.drawLine((int) randbed[1][0], (int) randbed[1][1],
                    (int) randbed[3][0], (int) randbed[3][1]);

            // Zeichne Kurve von P0 -> P1
            g2d.setColor(c);

            double[] k_alt = { randbed[0][0], randbed[0][1] };
            double dt = 1.0 / 32.0;
            for (double t = dt; t < 1.0; t += dt) {
                double[] k = kurve(t);
                g2d.drawLine((int) k_alt[0], (int) k_alt[1], (int) k[0],
                        (int) k[1]);
                k_alt = k;

            }

        }

    }

    private double[] kurve(double t) {
        return Matrix.matMult(new double[][] { bindeF(t) }, randbed)[0];
    }

    private double[] bindeF(double t) {

        double t1 = 2 * (t * t * t) - 3 * (t * t) + 1;
        double t2 = -2 * (t * t * t) + 3 * (t * t);
        double t3 = (t * t * t) - 2 * (t * t) + t;
        double t4 = (t * t * t) - (t * t);
        return new double[] { t1, t2, t3, t4 };
    }

    @Override
    public void update(Punktliste p) {
        anzahl = p.getSize();

        if (anzahl >= 4) {
            double[] p0 = { p.getPunktAt(0).getX(), p.getPunktAt(0).getY() };
            double[] p1 = { p.getPunktAt(1).getX(), p.getPunktAt(1).getY() };
            double[] t0 = { p.getPunktAt(2).getX(), p.getPunktAt(2).getY() };
            double[] t1 = { p.getPunktAt(3).getX(), p.getPunktAt(3).getY() };
            randbed = new double[][] { p0, p1, t0, t1 };

        }
    }
}
