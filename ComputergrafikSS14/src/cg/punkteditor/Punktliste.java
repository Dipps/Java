package cg.punkteditor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Punktliste {

    private final ArrayList<Punkt> punkte = new ArrayList<>();

    private final Color highLight = Color.RED;

    private Color line = Color.BLUE;

    private boolean drawLine = true;

    private int markedPoint = -1; // markierter Punkt

    private final int box = 25; // Punktdurchmesser * Punktdurchmesser

    public void draw(final Graphics g) {
        for (final Punkt p : punkte) {
            p.draw(g);
        }

        if (markedPoint > -1) {
            punkte.get(markedPoint).draw(g, highLight);
        }

        // Linie zeichnen
        if (drawLine) {
            g.setColor(line);
            for (int i = 1; i < punkte.size(); i++) {
                final Punkt p0 = punkte.get(i - 1);
                final Punkt p1 = punkte.get(i);
                g.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
            }
        }

    }

    public void add(final Punkt p) {
        if (markedPoint < 0) {
            punkte.add(p);
        }
    }

    public void removeMarked() {
        if (markedPoint > -1) {
            punkte.remove(markedPoint);
            markedPoint = -1;
        }
    }

    public void mark(final int x, final int y) {
        int dq = Integer.MAX_VALUE;
        markedPoint = -1;

        for (int i = 0; i < punkte.size(); i++) {
            final int abst = punkte.get(i).abstandQ(x, y);
            if (abst < box && abst < dq) {
                markedPoint = i;
                dq = abst;
            }
        }

    }

    public void moveMarked(final int x, final int y) {
        if (markedPoint > -1) {
            punkte.get(markedPoint).setPosition(x, y);
        }
    }

    public int getSize() {
        return punkte.size();
    }

    public Punkt getPunktAt(final int i) {
        return punkte.get(i);
    }

    public void setDrawLine(boolean b) {
        drawLine = b;
    }

    public void setLineColor(Color c) {
        this.line = c;
    }

}
