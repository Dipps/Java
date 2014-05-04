package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;

public class Leuchtdiode implements IGeometrie {
    private int x; // Mittelpunkt
    private int y; // Mittelpunkt
    private double[][] matrix;
    private int radius;
    private Color color;

    public Leuchtdiode(int x, int y, double[][] matrix, int radius, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.radius = radius;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {

        // final Graphics2D g2d = (Graphics2D) g;

        // Diode Zeichnen
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

        // Pins Zeichen
        int r = 5;
        int distance = radius / 2;
        g.fillOval((x - r) - distance, y - r, 2 * r, 2 * r);
        g.fillOval((x - r) + distance, y - r, 2 * r, 2 * r);

    }

    @Override
    public void setTransform(double[][] m) {
        // String s = "";
        // double[] vector = { x, y, 1.0 };
        // Matrix.print(s, vector);
        // double[] result = Matrix.matMult(m, vector);
        // Matrix.print(s, m);
        // x = (int) result[0];
        // y = (int) result[1];
        // Matrix.print(s, result);

    }
}
