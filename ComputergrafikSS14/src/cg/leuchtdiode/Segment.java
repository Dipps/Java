package cg.leuchtdiode;

import java.awt.Color;

public class Segment extends Geometrieliste implements IGeometrie {

    private int xPos;
    private int yPos;
    private int anzahl;
    private final int ledDuchmesser;
    private final Color color;

    private final int abstand;

    /**
     * @param x
     * @param y
     * @param length
     * @param color
     * @param matrix
     */
    public Segment(int x, int y, int anzahl, int ledDuchmesser, Color color) {
        super();
        if (anzahl <= 0) {
            throw new IllegalArgumentException("anzahl muss > 0 sein");
        }
        this.xPos = x;
        this.yPos = y;
        this.anzahl = anzahl;
        this.ledDuchmesser = ledDuchmesser;
        this.color = color;
        this.abstand = ledDuchmesser + ledDuchmesser / 2;
        createSegment();
    }

    private void createSegment() {
        System.out.println(anzahl);
        int x = xPos;
        int y = yPos;

        addGeometrie(new Leuchtdiode(x, y, ledDuchmesser, color));
        addGeometrie(new Leuchtdiode(x + (anzahl - 1) * abstand, y,
                ledDuchmesser, color));
        x += abstand;

        for (int i = 1; i < anzahl - 1; i++) {
            addGeometrie(new Leuchtdiode(x, y + abstand, ledDuchmesser, color));
            addGeometrie(new Leuchtdiode(x, y - abstand, ledDuchmesser, color));
            x += abstand;
        }

    }

    private void updateSegment() {
        leds.clear();
        createSegment();
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        updateSegment();
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        if (anzahl <= 0) {
            this.anzahl = 1;
        } else {
            this.anzahl = anzahl;
        }
        updateSegment();
    }

    public void rotate(int alpha) {

    }
}
