package cg.leuchtdiode;

import java.awt.Color;

import cg.matrix.Matrix;

public class Segment extends Geometrieliste implements IGeometrie {

    private int xPos;
    private int yPos;
    private final int[] endPos = { 0, 0 };
    private int anzahl;
    private final int ledDurchmesser;
    private Color color;
    private final int ledWinkel;
    private double[][] matrix = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0 } };

    private final int abstand;

    public Segment(int x, int y, int anzahl, int ledDurchmesser, Color color,
            int ledWinkel) {
        super();
        if (anzahl <= 0) {
            anzahl = 1;
            // throw new IllegalArgumentException("anzahl muss > 0 sein");
        }
        this.xPos = x;
        this.yPos = y;
        this.anzahl = anzahl;
        this.ledDurchmesser = ledDurchmesser;
        this.color = color;
        this.ledWinkel = ledWinkel;
        this.abstand = ledDurchmesser + ledDurchmesser / 2;
        createSegment();
    }

    public Segment(int x, int y, int anzahl, int ledDurchmesser, Color color,
            int ledWinkel, int winkel) {
        this(x, y, anzahl, ledDurchmesser, color, ledWinkel);
        rotate(winkel);
    }

    private void createSegment() {
        int x = 0; // Offset
        int y = 0; // Offset
        matrix[0][2] = xPos;
        matrix[1][2] = yPos;
        // Matrix.print("", matrix);

        double[] pos = { x, y, 1.0 };
        pos = Matrix.matMult(matrix, pos);
        addGeometrie(new Leuchtdiode((int) pos[0], (int) pos[1],
                ledDurchmesser, color, ledWinkel));

        pos[0] = 0 + (anzahl - 1) * abstand;
        pos[1] = 0;
        pos = Matrix.matMult(matrix, pos);
        addGeometrie(new Leuchtdiode((int) pos[0], (int) pos[1],
                ledDurchmesser, color, ledWinkel));
        endPos[0] = (int) pos[0];
        endPos[1] = (int) pos[1];
        x += abstand;

        for (int i = 1; i < anzahl - 1; i++) {
            pos[0] = x;
            pos[1] = y + abstand;
            pos = Matrix.matMult(matrix, pos);

            addGeometrie(new Leuchtdiode((int) pos[0], (int) pos[1],
                    ledDurchmesser, color, ledWinkel));

            pos[0] = x;
            pos[1] = y - abstand;
            pos = Matrix.matMult(matrix, pos);

            addGeometrie(new Leuchtdiode((int) pos[0], (int) pos[1],
                    ledDurchmesser, color, ledWinkel));
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
        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double[][] m = { { cos, sin, 0.0 }, { -sin, cos, 0.0 },
                { 0.0, 0.0, 1.0 } };
        matrix = Matrix.matMult(matrix, m);
        updateSegment();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.color = color;
    }

    public int getLength() {
        return anzahl * abstand;
    }

    public int getAbstand() {
        return abstand;
    }

    public int[] getEndPosition() {
        return endPos;
    }

}
