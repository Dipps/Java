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
    private final int ledWinkel;
    private final int ledDurchmesser = 15;
    private final Color color = Color.RED;

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

        segments.add(new Segment(x, y, anzVertikal, ledDurchmesser, color));
        int[] end = segments.get(0).getEndPosition();
        abstand = segments.get(0).getAbstand();

        segments.add(new Segment(end[0] + abstand, end[1] + abstand,
                anzHorizontal, ledDurchmesser, color, -schraegWinkel));

        end = segments.get(1).getEndPosition();
        segments.add(new Segment(end[0], end[1] + 2 * abstand, anzHorizontal,
                ledDurchmesser, color, -schraegWinkel));

        end = segments.get(2).getEndPosition();
        segments.add(new Segment(end[0] - abstand, end[1] + abstand,
                anzVertikal, ledDurchmesser, color, -180));

        end = segments.get(3).getEndPosition();
        segments.add(new Segment(end[0] - abstand, end[1] - abstand,
                anzHorizontal, ledDurchmesser, color, -schraegWinkel + 180));

        end = segments.get(4).getEndPosition();
        segments.add(new Segment(end[0], end[1] - 2 * abstand, anzHorizontal,
                ledDurchmesser, color, -schraegWinkel + 180));

        end = segments.get(4).getEndPosition();
        segments.add(new Segment(end[0] + abstand, end[1] - abstand,
                anzVertikal, ledDurchmesser, color));

        end = segments.get(2).getEndPosition();
        segments.add(new Segment(end[0] + 4 * abstand, end[1] + 2 * abstand,
                anzHorizontal / 2, ledDurchmesser, color, -schraegWinkel + 180));

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
}
