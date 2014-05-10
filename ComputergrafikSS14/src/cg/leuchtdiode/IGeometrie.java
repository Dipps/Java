package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;

public interface IGeometrie {
    public void draw(Graphics g);

    public void setColor(Color color);

    public void setTransformation(double[][] matrix);
}
