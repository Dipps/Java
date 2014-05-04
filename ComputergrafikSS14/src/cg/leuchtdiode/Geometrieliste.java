package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Geometrieliste implements IGeometrie {

    private final ArrayList<Leuchtdiode> dioden = new ArrayList<>();

    public Geometrieliste() {
        // TODO Auto-generated constructor stub
    }

    public void addLeuchtDiode(Leuchtdiode ld) {
        dioden.add(ld);
    }

    public void removeLeuchtdiode(Leuchtdiode ld) {
        dioden.remove(ld);
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setColor(Color c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTransform(double[][] m) {
        // TODO Auto-generated method stub

    }

}
