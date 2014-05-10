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
    private double[][] matrix;

    public Leuchtdiode(int x, int y, int durchmesser, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.durchmesser = durchmesser;
        this.color = color;
        matrix = new double[][] { { 1.0, 0.0, x }, { 0.0, 1.0, y },
                { 0.0, 0.0, 1.0 } };
    }

    @Override
    public void draw(Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        int radius = durchmesser / 2;

        // Matrix.print("", matrix);
        AffineTransform at1 = new AffineTransform(matrix[0][0], matrix[1][0],
                matrix[0][1], matrix[1][1], matrix[0][2], matrix[1][2]);

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

}
