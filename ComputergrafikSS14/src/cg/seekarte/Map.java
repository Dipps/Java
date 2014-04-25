package cg.seekarte;

import javax.swing.ImageIcon;

import cg.matrix.Matrix;

public class Map {

    private final ImageIcon icon;

    // Transformationsmatrix
    private final double[][] transform;

    // Inverse Transformationsmatrix
    private final double[][] inverseTransform;

    public Map(ImageIcon icon, double[] p1, double[] p2, double[] p3) {
        this.icon = icon;
        this.transform = MapCalibration.calculateMapCalibrationMatrix(p1, p2,
                p3);
        inverseTransform = Matrix.invertiereMatrix(transform);
    }

    public ImageIcon getImageIcon() {
        return icon;
    }

    public double[][] getTransform() {
        return transform;
    }

    public double[][] getInverseTransform() {
        return inverseTransform;
    }

}
