package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;

import cg.punkteditor.Punktliste;

public class BezierKurve implements IGeometrieView {

    private boolean draw = false;
    private int anzahl;
    private double[][] b;
    double[] x, y;

    @Override
    public void draw(Graphics g, Punktliste p, Color color) {
        if (!draw) {
            return;
        }
        update(p);

        if (anzahl > 1) {
            double[] cAlt = { x[0], y[0] };

            int samples = 16 * anzahl;
            double dt = 1d / samples;
            for (double t = 0.0; t <= 1.0; t += dt) {

                double[] c = getXYvalues(t);

                g.setColor(Color.RED);
                g.drawLine((int) (cAlt[0]), (int) (cAlt[1]), (int) (c[0]),
                        (int) (c[1]));
                cAlt = c;
                // g.fillOval((int) (c[0] - 2), (int) (c[1] - 2), 4, 4);
                // g.setColor(Color.CYAN);
                // g.fillOval((int) (cAlt[0] - 4), (int) (cAlt[1] - 4), 8, 8);

            }

        }

    }

    public double evaluate(double t, double[] initialValues) {
        init(initialValues);
        for (int j = 1; j < anzahl; ++j) {
            for (int i = 0; i < anzahl - j; ++i) {
                b[j][i] = (1 - t) * b[j - 1][i] + b[j - 1][i + 1] * t;
            }
        }
        return (b[anzahl - 1][0]);
    }

    private void init(double[] initialValues) {
        for (int i = 0; i < anzahl; i++) {
            b[0][i] = initialValues[i];
        }

    }

    public double[] getXYvalues(double t) {
        double xVal = evaluate(t, x);
        double yVal = evaluate(t, y);
        return new double[] { xVal, yVal };
    }

    @Override
    public void update(Punktliste p) {
        anzahl = p.getSize();
        b = new double[anzahl][anzahl];

        if (anzahl > 1) {
            x = new double[anzahl];
            y = new double[anzahl];

            for (int i = 0; i < anzahl; ++i) {
                x[i] = p.getPunktAt(i).getX();
                y[i] = p.getPunktAt(i).getY();
            }
        }
    }

    public boolean getDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

}
