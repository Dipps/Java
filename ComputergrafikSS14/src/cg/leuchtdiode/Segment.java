package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;

public class Segment extends Geometrieliste implements IGeometrie {

    private final int x;
    private final int y;
    private final int length;
    private final int ledDurchmesser;
    private final Color color;
    private final double[][] matrix;

    /**
     * @param x
     * @param y
     * @param length
     * @param color
     * @param matrix
     */
    public Segment(int x, int y, int length, int ledDuchmesser, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.length = length;
        this.ledDurchmesser = ledDuchmesser;
        this.color = color;
        matrix = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 } };
        createSegment();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawLine(x + ledDurchmesser, y - ledDurchmesser, x + length
                - ledDurchmesser, y - ledDurchmesser);
        g.drawLine(x, y, x + length, y);
        // g.drawLine(x, y, x + length, y);
    }

    public void createSegment() {
        int anzahl = length / ledDurchmesser - 2;

        addGeometrie(new Leuchtdiode(x, y, ledDurchmesser, color));
        for (int i = 1; i < anzahl; i++) {
            int distance = (length / anzahl) * i;
            addGeometrie(new Leuchtdiode(x + distance, y, ledDurchmesser, color));
        }
        addGeometrie(new Leuchtdiode(x + length, y, ledDurchmesser, color));

    }
}
