package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;

public class Segment extends Geometrieliste implements IGeometrie {

    private final int x;
    private final int y;
    private final int length;
    private final int ledDurchmesser;
    private final Color color;

    // private final double[][] matrix;

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
        // matrix = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
        // { 0.0, 0.0, 1.0 } };
        createSegment();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawLine(x, y, x + length, y);

        int abstand = ledDurchmesser + ledDurchmesser / 2;
        g.drawLine(x + abstand, y - abstand, x + length - abstand, y - abstand);

        g.drawLine(x + abstand, y + abstand, x + length - abstand, y + abstand);

        for (int i = abstand; i < length; i += abstand) {
            g.drawLine(x + i, y - 5, x + i, y + 5);
        }

        for (int i = 2 * abstand; i < length; i += abstand) {
            if (i + abstand <= length - abstand) {
                g.drawLine(x + i, (y - 5) + abstand, x + i, (y + 5) + abstand);
            }
        }
    }

    public void createSegment() {

        // Erste und letzte LED erstellen
        addGeometrie(new Leuchtdiode(x, y, ledDurchmesser, color));
        addGeometrie(new Leuchtdiode(x + length, y, ledDurchmesser, color));

        int abstand = ledDurchmesser + ledDurchmesser / 2;
        addGeometrie(new Leuchtdiode(x + abstand, y + abstand, ledDurchmesser,
                color));
        addGeometrie(new Leuchtdiode(x - abstand + length, y + abstand,
                ledDurchmesser, color));

        addGeometrie(new Leuchtdiode(x + abstand, y - abstand, ledDurchmesser,
                color));
        addGeometrie(new Leuchtdiode(x - abstand + length, y - abstand,
                ledDurchmesser, color));

        for (int i = abstand; i <= length; i += abstand) {
            if (i + abstand <= length - abstand) {
                addGeometrie(new Leuchtdiode(x + i, y + abstand,
                        ledDurchmesser, color));
                // addGeometrie(new Leuchtdiode(x + i, y - 2 * ledDurchmesser,
                // ledDurchmesser, color));
            }
        }
    }
}
