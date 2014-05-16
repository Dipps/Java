package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Geometrieliste implements IGeometrie {

    protected final ArrayList<IGeometrie> leds;

    /**
     * @param leds
     */
    public Geometrieliste() {
        super();
        leds = new ArrayList<>();
    }

    public void addGeometrie(IGeometrie g) {
        leds.add(g);
    }

    public void removeGeometrie(IGeometrie g) {
        leds.remove(g);
    }

    @Override
    public void draw(Graphics g) {
        for (IGeometrie i : leds) {
            i.draw(g);
        }

    }

    @Override
    public void setColor(Color color) {
        for (IGeometrie i : leds) {
            i.setColor(color);
        }

    }

    @Override
    public void setTransformation(double[][] matrix) {
        for (IGeometrie i : leds) {
            i.setTransformation(matrix);
        }

    }

}
