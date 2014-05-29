package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;

import cg.matrix.Matrix;
import cg.punkteditor.Punktliste;

public class EingespannteHSpline extends HermiteSpline {

    @Override
    public void draw(Graphics g, Punktliste p, Color color) {
        if (!draw) {
            return;
        }

        update(p);

        if (anzahl > 3) {

            punkte = new double[anzahl][2];
            for (int i = 0; i < anzahl; ++i) {
                punkte[i][0] = p.getPunktAt(i).getX();
                punkte[i][1] = p.getPunktAt(i).getY();
            }

            pStrich = Matrix.matMult(aInvB, punkte);

            for (int i = 1; i < anzahl - 2; ++i) {

                int xa = (int) punkte[i][0];
                int ya = (int) punkte[i][1];

                int samples = 32;
                double dt = 1d / samples;

                for (double t = 0d; t <= 1.0; t += dt) {

                    int[] c = kurve(t, i);

                    g.setColor(color);
                    g.drawLine(xa, ya, c[0], c[1]);
                    xa = c[0];
                    ya = c[1];
                }
            }

            // @formatter:off
            g.setColor(Color.GREEN);
            g.drawLine((int) punkte[0][0], (int) punkte[0][1],
                       (int) punkte[1][0], (int) punkte[1][1]);
            g.drawLine((int) punkte[anzahl - 2][0], (int) punkte[anzahl - 2][1], 
                       (int) punkte[anzahl - 1][0], (int) punkte[anzahl - 1][1]);
            // @formatter:on

        }

    }

    @Override
    public void update(Punktliste p) {
        clampSpline(p);
    }

    private void clampSpline(Punktliste p) {
        anzahl = p.getSize();

        if (anzahl > 3) {
            a = new double[anzahl][anzahl];
            a[0][0] = 1d;
            a[anzahl - 1][anzahl - 1] = 1d;

            b = new double[anzahl][anzahl];
            b[0][0] = 1d;
            b[anzahl - 1][anzahl - 1] = 1d;

            for (int i = 1; i < anzahl - 1; ++i) {
                a[i][i - 1] = 1d; // Z1: 1(Pi-1)
                a[i][i] = 4d; // Z1: 4(Pi)
                a[i][i + 1] = 1d; // Z1: 1(Pi+1)

                b[i][i - 1] = -3d; // Z1: -3(Pi-1)
                b[i][i + 1] = 3d; // Z1: 3(Pi+1)
            }

            System.out.println("::::::::::::::::::::::::");
            Matrix.print("A: ", a);
            System.out.println("------------------------");
            Matrix.print("B: ", b);
            System.out.println("::::::::::::::::::::::::");
            aInvB = Matrix.matMult(Matrix.invertiereMatrix(a), b);
        }

    }

    private int[] kurve(double t, int i) {
        double[] h = bindeF(t);
        int[] result = new int[2];

        // @formatter:off
        result[0] = (int) (h[0] * punkte[i][0] 
                         + h[1] * punkte[i+1][0] 
                         + h[2] * pStrich[i][0] 
                         + h[3] * pStrich[i+1][0]);
        
        result[1] = (int) (h[0] * punkte[i][1] 
                         + h[1] * punkte[i+1][1] 
                         + h[2] * pStrich[i][1] 
                         + h[3] * pStrich[i+1][1]);
        // @formatter:on

        return result;
    }

}
