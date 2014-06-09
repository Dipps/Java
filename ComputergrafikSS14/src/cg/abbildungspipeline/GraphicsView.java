package cg.abbildungspipeline;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class GraphicsView extends JLabel implements IAbbListener {

    private final AbbModel model;

    private int width, height;
    private final int viewDistance = 500;
    private final double[] eye;

    public GraphicsView(AbbModel model) {
        this.model = model;
        this.width = getWidth();
        this.height = getHeight();
        eye = new double[] { width / 2, height / 2, viewDistance };

        double[] center = { width / 2, height / 2, 0, 1.0 };
        this.model.setCenter(center);

    }

    @Override
    protected void paintComponent(final Graphics g) {
        update();
        double[] center = model.getCenter();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(0, 0, width, height);
        g2d.fillOval((int) center[0] - 2, (int) center[1] - 2, 4, 4);

        g2d.setColor(Color.BLACK);
        double[] pX = calculatePoint2D(eye, model.getX());
        g2d.drawString("X", (int) pX[0], (int) pX[1]);
        g2d.fillOval((int) pX[0] - 2, (int) pX[1] - 2, 4, 4);

        g2d.setColor(Color.BLUE);
        double[] pY = calculatePoint2D(eye, model.getY());
        g2d.drawString("Y", (int) pY[0], (int) pY[1]);
        g2d.fillOval((int) pY[0] - 2, (int) pY[1] - 2, 4, 4);

        g2d.setColor(Color.RED);
        double[] pZ = calculatePoint2D(eye, model.getZ());
        g2d.drawString("Z", (int) pZ[0], (int) pZ[1]);
        g2d.fillOval((int) pZ[0] - 2, (int) pZ[1] - 2, 4, 4);

        g2d.setStroke(new BasicStroke(2));
        drawPlane(pX, pY, g2d, Color.BLUE); // XY Ebene
        drawPlane(pX, pZ, g2d, Color.GREEN); // XZ Ebene
        drawPlane(pY, pZ, g2d, Color.RED); // YZ Ebene

        g2d.setStroke(new BasicStroke(1));
        drawAxis(model.getXAxe(), g2d, Color.BLACK);
        drawAxis(model.getYAxe(), g2d, Color.BLUE);
        drawAxis(model.getZAxe(), g2d, Color.RED);

    }

    private void drawPlane(double[] p1, double[] p2, Graphics2D g2d, Color c) {
        double[] center = model.getCenter();
        g2d.setColor(c);
        g2d.drawLine((int) center[0], (int) center[1], (int) p1[0], (int) p1[1]);
        g2d.drawLine((int) p1[0], (int) p1[1], (int) p2[0], (int) p2[1]);
        g2d.drawLine((int) p2[0], (int) p2[1], (int) center[0], (int) center[1]);
    }

    private void drawAxis(double[] axis, Graphics2D g2d, Color c) {
        double[] center = model.getCenter();
        double[] p = calculatePoint2D(eye, axis);
        g2d.setColor(c);
        g2d.drawLine((int) center[0], (int) center[1], (int) p[0], (int) p[1]);
        g2d.drawLine((int) center[0], (int) center[1],
                (int) (-p[0] + 2 * center[0]), (int) (-p[1] + 2 * center[1]));
    }

    private void update() {
        width = getWidth();
        height = getHeight();

        eye[0] = width / 2;
        eye[1] = height / 2;

        double[] center = { width / 2, height / 2, 0, 1.0 };
        model.setCenter(center);

    }

    @Override
    public void AbbModelChanged(AbbModel model) {
        repaint();
    }

    /**
     * @param eye
     *            Position des Blickpunktes
     * @param p
     *            3d Punkt der transformiert werden soll
     * @return gibt 3d Punkt als 2d Koordinaten zurück
     */
    public double[] calculatePoint2D(double[] eye, double[] p) {

        double[] result = new double[2];

        //@formatter:off
        // ----------------------------------------
        // Formula to solve Sx (Result)
        // ----------------------------------------
        // Ez = distance from eye to the center of the screen
        // Ex = X coordinate of the eye
        // Px = X coordinate of the 3D point
        // Pz = Z coordinate of the 3D point
        //
        //              Ez*(Px-Ex)
        // Sx = ----------------------- + Ex
        //               Ez+Pz
        //@formatter:on
        result[0] = (eye[2] * (p[0] - eye[0])) / (eye[2] + p[2]) + eye[0];

        //@formatter:off
        // ----------------------------------------
        // Formula to solve Sy (Result)
        // ----------------------------------------
        // Ez = distance from eye to the center of the screen
        // Ey = Y coordinate of the eye
        // Py = Y coordinate of the 3D point
        // Pz = Z coordinate of the 3D point
        //
        //             Ez*(Py-Ey)
        // Sy = ------------------- + Ey
        //              Ez+Pz
        //@formatter:on
        result[1] = (eye[2] * (p[1] - eye[1])) / (eye[2] + p[2]) + eye[1];

        return result;
    }

}
