package cg.punkteditor;

import java.awt.Color;
import java.awt.Graphics;

public class Punkt {

    private int x;
    private int y;
    private int durchmesser = 5;
    private Color color = Color.BLACK;

    /**
     * @param x
     * @param y
     */
    public Punkt(int x, int y) {
        super();
        this.x = x;
        this.y = y;

    }

    public void draw(Graphics g) {
        int radius = durchmesser / 2;
        g.setColor(color);
        g.fillOval(x - radius, y - radius, durchmesser, durchmesser);
    }

    public void draw(Graphics g, Color c) {
        int radius = durchmesser / 2;
        g.setColor(c);
        g.fillOval(x - radius, y - radius, durchmesser, durchmesser);
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public int abstandQ(int ax, int ay) {
        int dx = x - ax;
        int dy = y - ay;
        return dx * dx + dy * dy;
    }

    public double abstand(int x, int y) {
        return Math.sqrt(abstandQ(x, y));
    }

}
