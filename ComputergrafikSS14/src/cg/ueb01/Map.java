package cg.ueb01;

import javax.swing.ImageIcon;

public class Map
{

    ImageIcon image;

    final private double[][] matrix;

    final private double[][] iMatrix;

    public Map(final ImageIcon i)
    {
        image = i;
        matrix = new double[2][2];
        iMatrix = new double[2][2];

    }

}
