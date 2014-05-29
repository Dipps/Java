package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;

import cg.matrix.Matrix;
import cg.punkteditor.Punktliste;

public class HermiteSpline implements IGeometrieView {

    private int anzahl;
    private double[][] a, b, aInvB;
    private double[][] pStrich, punkte;

    @Override
    public void update(Punktliste p) {
        anzahl = p.getSize();

        if (anzahl > 2) {
            // MP - Form: 1(Pi-1) + 4(Pi) + 1(Pi+1) = -3(Pi-1) + 3(Pi+1)
            a = new double[anzahl][anzahl];
            a[0][anzahl - 1] = 1d; // Z1: 1(Pi-1)
            a[0][0] = 4d; // Z1: 4(Pi)
            a[0][1] = 1d; // Z1: 1(Pi+1)

            b = new double[anzahl][anzahl];
            b[0][anzahl - 1] = -3d; // Z1: -3(Pi-1)
            b[0][1] = 3d; // Z1: 3(Pi+1)

            for (int i = 1; i < anzahl - 1; ++i) {
                a[i][i - 1] = 1d; // Z1: 1(Pi-1)
                a[i][i] = 4d; // Z1: 4(Pi)
                a[i][i + 1] = 1d; // Z1: 1(Pi+1)

                b[i][i - 1] = -3d; // Z1: -3(Pi-1)
                b[i][i + 1] = 3d; // Z1: 3(Pi+1)
            }

            a[anzahl - 1][anzahl - 2] = 1d; // ZE: 1(Pi-1)
            a[anzahl - 1][anzahl - 1] = 4d; // ZE: 4(Pi)
            a[anzahl - 1][0] = 1d; // ZE: 1(Pi+1)

            b[anzahl - 1][anzahl - 2] = -3d; // ZE: -3(Pi-1)
            b[anzahl - 1][0] = 3d; // ZE: 3(Pi+1)

            System.out.println("::::::::::::::::::::::::");
            Matrix.print("A: ", a);
            System.out.println("------------------------");
            Matrix.print("B: ", b);
            System.out.println("::::::::::::::::::::::::");
            aInvB = Matrix.matMult(Matrix.invertiereMatrix(a), b);
        }
    }

    @Override
    public void draw(Graphics g, Punktliste p, Color color) {
        update(p);

        if (anzahl > 2) {

            punkte = new double[anzahl][2];
            for (int i = 0; i < anzahl; ++i) {
                punkte[i][0] = p.getPunktAt(i).getX();
                punkte[i][1] = p.getPunktAt(i).getY();
            }

            pStrich = Matrix.matMult(aInvB, punkte);

            for (int i = 0; i < anzahl; ++i) {

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
        }
    }

    private int[] kurve(double t, int i) {

        double[] h = bindeF(t);
        int[] result = new int[2];
        int iMod = (i + 1) % anzahl;

        // @formatter:off
        result[0] = (int) (h[0] * punkte[i][0] 
                         + h[1] * punkte[iMod][0] 
                         + h[2] * pStrich[i][0] 
                         + h[3] * pStrich[iMod][0]);
        
        result[1] = (int) (h[0] * punkte[i][1] 
                         + h[1] * punkte[iMod][1] 
                         + h[2] * pStrich[i][1] 
                         + h[3] * pStrich[iMod][1]);
        // @formatter:on

        return result;
    }

    private double[] bindeF(double t) {

        double t1 = 2 * (t * t * t) - 3 * (t * t) + 1;
        double t2 = -2 * (t * t * t) + 3 * (t * t);
        double t3 = (t * t * t) - 2 * (t * t) + t;
        double t4 = (t * t * t) - (t * t);
        return new double[] { t1, t2, t3, t4 };
    }

}
