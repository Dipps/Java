package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SiebenSegmentAnzeige {

    private int posX;
    private int posY;
    private int schraegWinkel;
    private int anzHorizontal;
    private int anzVertikal;
    private int ledWinkel;
    private final int ledDurchmesser = 15;
    private final Color color = Color.RED;
    private int zahl = 0;

    private final ArrayList<Segment> segments = new ArrayList<>();

    /**
     * @param posX
     * @param posY
     * @param hoehe
     * @param breite
     * @param schraegWinkel
     * @param anzHorizontal
     * @param anzVertikal
     * @param ledWinkel
     */
    public SiebenSegmentAnzeige(int posX, int posY, int schraegWinkel,
            int anzHorizontal, int anzVertikal, int ledWinkel) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.schraegWinkel = schraegWinkel;
        this.anzHorizontal = anzHorizontal;
        this.anzVertikal = anzVertikal;
        this.ledWinkel = ledWinkel;

        createSegments();
    }

    public void createSegments() {
        int x = posX;
        int y = posY;
        int abstand = 0;

        // Segment a
        segments.add(new Segment(x, y, anzVertikal, ledDurchmesser, color,
                ledWinkel + 90));

        // Segment b
        int[] end = segments.get(0).getEndPosition();
        abstand = segments.get(0).getAbstand();
        segments.add(new Segment(end[0] + abstand, end[1] + abstand,
                anzHorizontal, ledDurchmesser, color, ledWinkel, -schraegWinkel));

        // Segment c
        end = segments.get(1).getEndPosition();
        segments.add(new Segment(end[0], end[1] + 2 * abstand, anzHorizontal,
                ledDurchmesser, color, ledWinkel, -schraegWinkel));

        // Segment d
        end = segments.get(2).getEndPosition();
        segments.add(new Segment(end[0] - abstand, end[1] + abstand,
                anzVertikal, ledDurchmesser, color, ledWinkel + 90, -180));

        // Segment e
        end = segments.get(3).getEndPosition();
        segments.add(new Segment(end[0] - abstand, end[1] - abstand,
                anzHorizontal, ledDurchmesser, color, ledWinkel,
                -schraegWinkel + 180));

        // Segment f
        end = segments.get(4).getEndPosition();
        segments.add(new Segment(end[0], end[1] - 2 * abstand, anzHorizontal,
                ledDurchmesser, color, ledWinkel, -schraegWinkel + 180));

        // Segment g
        end = segments.get(4).getEndPosition();
        segments.add(new Segment(end[0] + abstand, end[1] - abstand,
                anzVertikal, ledDurchmesser, color, ledWinkel + 90));

        // Segment Punkt
        end = segments.get(2).getEndPosition();
        segments.add(new Segment(end[0] + 4 * abstand, end[1] + 2 * abstand,
                anzHorizontal / 2, ledDurchmesser, color, ledWinkel,
                -schraegWinkel + 180));

        initSegmentColor();
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void update() {
        segments.clear();
        createSegments();
    }

    public void draw(Graphics g) {
        for (Segment i : segments) {
            i.draw(g);
        }
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
        update();
    }

    public int getSchraegWinkel() {
        return schraegWinkel;
    }

    public void setSchraegWinkel(int alpha) {
        this.schraegWinkel = alpha;
        update();
    }

    public void scaleHeight(int factor) {
        if (anzHorizontal <= 0) {
            anzHorizontal = 1;
        } else {
            this.anzHorizontal += factor;
        }
        update();
    }

    public void scaleWidth(int factor) {
        if (anzVertikal <= 0) {
            anzVertikal = 1;
        } else {
            this.anzVertikal += factor;
        }
        update();
    }

    public int getLedWinkel() {
        return ledWinkel;
    }

    public void setLedWinkel(int alpha) {
        this.ledWinkel = alpha;
        update();
    }

    public void setNumber(int n) {
        n %= 10;
        // System.out.println(n);
        zahl = n;
        update();
    }

    public int getNumber() {
        return zahl;
    }

    private void initSegmentColor() {
        switch (zahl) {
        case 0:
            // System.out.println("0");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(4).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(7).setColor(Color.GREEN);
            break;
        case 1:
            // System.out.println("1");
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            break;
        case 2:
            // System.out.println("2");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(4).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            segments.get(7).setColor(Color.GREEN);
            break;
        case 3:
            // System.out.println("3");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            break;
        case 4:
            // System.out.println("4");
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            segments.get(7).setColor(Color.GREEN);
            break;
        case 5:
            // System.out.println("5");
            segments.get(0).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            break;
        case 6:
            // System.out.println("6");
            segments.get(0).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(4).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            segments.get(7).setColor(Color.GREEN);
            break;
        case 7:
            // System.out.println("7");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            break;
        case 8:
            // System.out.println("8");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(4).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            segments.get(7).setColor(Color.GREEN);
            break;
        case 9:
            // System.out.println("9");
            segments.get(0).setColor(Color.GREEN);
            segments.get(1).setColor(Color.GREEN);
            segments.get(2).setColor(Color.GREEN);
            segments.get(3).setColor(Color.GREEN);
            segments.get(5).setColor(Color.GREEN);
            segments.get(6).setColor(Color.GREEN);
            break;
        default:
            System.out.println("Default");
            segments.get(0).setColor(color);
            segments.get(1).setColor(color);
            segments.get(2).setColor(color);
            segments.get(3).setColor(color);
            segments.get(4).setColor(color);
            segments.get(5).setColor(color);
            segments.get(6).setColor(color);
            break;
        }
    }
}
