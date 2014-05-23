package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import cg.matrix.Matrix;

public class Leuchtdiode implements IGeometrie {
    private int x; // Mittelpunkt
    private int y; // Mittelpunkt
    private int durchmesser;
    private Color color;
    private double[][] matrix = new double[][] { { 1.0, 0.0, 0.0 },
            { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };;
    // private final int ledWinkel;
    private final double PINDISTANCE = 2.54;
    private final double[][] bohrPos;

    public Leuchtdiode(int x, int y, int durchmesser, Color color, int ledWinkel) {
        super();
        this.x = x;
        this.y = y;
        this.durchmesser = durchmesser;
        this.color = color;
        // this.ledWinkel = ledWinkel;
        // matrix = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
        // { 0.0, 0.0, 1.0 } };
        double cos = Math.cos(Math.toRadians(ledWinkel));
        double sin = Math.sin(Math.toRadians(ledWinkel));

        double[][] mR = { { cos, sin, 0.0 }, { -sin, cos, 0.0 },
                { 0.0, 0.0, 1.0 } };
        double[][] mT = new double[][] { { 1.0, 0.0, x }, { 0.0, 1.0, y },
                { 0.0, 0.0, 1.0 } };
        matrix = Matrix.matMult(mT, mR);

        bohrPos = new double[2][2];
    }

    @Override
    public void draw(Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        int radius = durchmesser / 2;

        // Matrix.print("", matrix);
        AffineTransform at1 = new AffineTransform(matrix[0][0], matrix[1][0],
                matrix[0][1], matrix[1][1], matrix[0][2], matrix[1][2]);

        // at1.rotate(Math.toRadians(ledWinkel));
        g2d.transform(at1);

        // Diode Zeichnen
        g2d.setColor(color);
        g2d.fillOval(-radius, -radius, 2 * radius, 2 * radius);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(-radius, -radius, 2 * radius, 2 * radius);

        // Pins Zeichen
        int pinRadius = durchmesser / 14;
        int distance = radius / 2;
        g2d.fillOval((-pinRadius) + distance, -pinRadius, 2 * pinRadius,
                2 * pinRadius);

        g2d.fillOval((-pinRadius) - distance, -pinRadius, 2 * pinRadius,
                2 * pinRadius);

        AffineTransform at2 = new AffineTransform();
        g2d.setTransform(at2);
        // g2d.drawLine(0, 0, getX(), getY());
        updateBohrungen();
        // Draw Correct PinPosition
        // g.drawLine(0, 0, x, y);
        // g.drawLine(x, y, (int) bohrPos[0][0], (int) bohrPos[1][0]);
        // g.drawLine(x, y, (int) bohrPos[0][1], (int) bohrPos[1][1]);
    }

    public void scale(double factor) {
        double[][] m = { { factor + 1.0, 0.0, 0.0 },
                { 0.0, factor + 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
        matrix = Matrix.matMult(matrix, m);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        matrix[0][2] = x;
        matrix[1][2] = y;
    }

    public void rotate(double alpha) {

        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double[][] m = { { cos, sin, 0.0 }, { -sin, cos, 0.0 },
                { 0.0, 0.0, 1.0 } };

        matrix = Matrix.matMult(matrix, m);

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        matrix[0][2] = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        matrix[1][2] = y;
    }

    public int getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    @Override
    public void setTransformation(double[][] matrix) {
        this.matrix = matrix;
    }

    private void updateBohrungen() {
        double[] pos1 = { 0.0 - PINDISTANCE / 2, 0.0, 1.0 };
        double[] pos2 = { 0.0 + PINDISTANCE / 2, 0.0, 1.0 };
        pos1 = Matrix.matMult(matrix, pos1);
        pos2 = Matrix.matMult(matrix, pos2);

        bohrPos[0][0] = pos1[0];
        bohrPos[1][0] = pos1[1];

        bohrPos[0][1] = pos2[0];
        bohrPos[1][1] = pos2[1];
        // Matrix.print("", pos1);
        // Matrix.print("", pos2);
        // Matrix.print("", bohrPos);
    }

    public double[][] getBohrPosition() {
        return bohrPos;
    }
}
