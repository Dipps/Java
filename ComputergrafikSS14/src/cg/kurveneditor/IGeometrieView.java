package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;

import cg.punkteditor.Punktliste;

public interface IGeometrieView {
    public void draw(Graphics g, Punktliste p, Color c);

    public void update(Punktliste p);
}
