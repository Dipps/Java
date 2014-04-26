package cg.seekarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cg.matrix.Matrix;

public class MapController implements ActionListener, MouseMotionListener,
        MouseListener {

    private final MapModel model;

    public MapController(MapModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final String s = e.getActionCommand();

        if (s.equals("Lotse")) {
            model.changeSelectedMap(model.getLotseMap());
        } else if (s.equals("S09l")) {
            model.changeSelectedMap(model.getS09lMap());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMousePosition(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMousePosition(e);

    }

    private void updateMousePosition(final MouseEvent e) {
        model.setMouseX(e.getX());
        model.setMouseY(e.getY());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // System.out.println("LeftMouse: " + model.getMouseX() + " " +
            // model.getMouseY());

            double[][] m = model.getMapTransform();
            double[] v = { model.getMouseX(), model.getMouseY(), 1.0 };
            double[] result = Matrix.matMult(m, v);
            model.addPoint(result);

            // System.out.println("Add Point: " + result[0] + " " + result[1]);

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // TODO: Punkt löschen
            // System.out.println("RightMouse");
            // model.removePoint(0);
            // System.out.println("Remove Point");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
