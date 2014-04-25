package cg.seekarte;

import cg.matrix.Gauss;

public class MapCalibration {

    /**
     * Ermittelt die Transformationsmatrix für die Seekarte
     * 
     * @param p1
     *            1.Kalibrierungspunkt (L1, B1, x1, y1)
     * @param p2
     *            2.Kalibrierungspunkt (L2, B2, x2, y2)
     * @param p3
     *            3.Kalibrierungspunkt (L3, B3, x3, y3)
     * @return Transformationsmatrix als double[][]
     */
    public static double[][] calculateMapCalibrationMatrix(double[] p1,
            double[] p2, double[] p3) {

        double[][] lgs = initLGS(p1, p2, p3);
        double[] resultVector = Gauss.gauss(lgs);
        double[][] resultMatrix = generateMatrix(resultVector);

        return resultMatrix;
    }

    private static double[][] initLGS(double[] p1, double[] p2, double[] p3) {

        double[][] lgs = new double[6][7];
        lgs[0][0] = p1[2];
        lgs[0][1] = p1[3];
        lgs[0][2] = 1;
        lgs[0][3] = 0;
        lgs[0][4] = 0;
        lgs[0][5] = 0;
        lgs[0][6] = p1[0];

        lgs[1][0] = 0;
        lgs[1][1] = 0;
        lgs[1][2] = 0;
        lgs[1][3] = p1[2];
        lgs[1][4] = p1[3];
        lgs[1][5] = 1;
        lgs[1][6] = p1[1];

        lgs[2][0] = p2[2];
        lgs[2][1] = p2[3];
        lgs[2][2] = 1;
        lgs[2][3] = 0;
        lgs[2][4] = 0;
        lgs[2][5] = 0;
        lgs[2][6] = p2[0];

        lgs[3][0] = 0;
        lgs[3][1] = 0;
        lgs[3][2] = 0;
        lgs[3][3] = p2[2];
        lgs[3][4] = p2[3];
        lgs[3][5] = 1;
        lgs[3][6] = p2[1];

        lgs[4][0] = p3[2];
        lgs[4][1] = p3[3];
        lgs[4][2] = 1;
        lgs[4][3] = 0;
        lgs[4][4] = 0;
        lgs[4][5] = 0;
        lgs[4][6] = p3[0];

        lgs[5][0] = 0;
        lgs[5][1] = 0;
        lgs[5][2] = 0;
        lgs[5][3] = p3[2];
        lgs[5][4] = p3[3];
        lgs[5][5] = 1;
        lgs[5][6] = p3[1];

        return lgs;

    }

    private static double[][] generateMatrix(double[] v) {
        double[][] result = new double[3][3];
        result[0][0] = v[0];
        result[0][1] = v[1];
        result[0][2] = v[2];

        result[1][0] = v[3];
        result[1][1] = v[4];
        result[1][2] = v[5];

        result[2][0] = 0;
        result[2][1] = 0;
        result[2][2] = 1;

        return result;

    }

    /*
     * public static void main(String[] args) { double l1 = 55.75; double b1 =
     * 10.67; double x1 = 712; double y1 = 424; double[] p1 = { l1, b1, x1, y1
     * };
     * 
     * double l2 = 55.58; double b2 = 10.83; double x2 = 1649; double y2 = 1734;
     * double[] p2 = { l2, b2, x2, y2 };
     * 
     * double l3 = 55.58; double b3 = 10.67; double x3 = 859; double y3 = 1817;
     * double[] p3 = { l3, b3, x3, y3 };
     * 
     * double[][] transform = MapCalibration.calculateMapCalibrationMatrix(p1,
     * p2, p3);
     * 
     * String s = ""; System.out.println(); Matrix.print(s, transform);
     * System.out.println(s); }
     */
}
