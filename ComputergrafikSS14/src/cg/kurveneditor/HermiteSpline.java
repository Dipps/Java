package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;

import cg.punkteditor.Punktliste;

public abstract class HermiteSpline implements IGeometrieView {

    @Override
    public abstract void draw(Graphics g, Punktliste p, Color c);

    @Override
    public abstract void update(Punktliste p);

    protected int anzahl;
    protected double[][] a, b, aInvB;
    protected double[][] pStrich, punkte;

    protected double[] bindeF(double t) {

        double t1 = 2 * (t * t * t) - 3 * (t * t) + 1;
        double t2 = -2 * (t * t * t) + 3 * (t * t);
        double t3 = (t * t * t) - 2 * (t * t) + t;
        double t4 = (t * t * t) - (t * t);
        return new double[] { t1, t2, t3, t4 };
    }
}
