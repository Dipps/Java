package cg.seekarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cg.punkteditor.Punkt;

public class MapController implements ActionListener, MouseMotionListener, MouseListener
{

    private final MapModel model;

    private boolean mouseIn = false;

    public MapController(final MapModel model)
    {
        this.model = model;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {

        final String s = e.getActionCommand();

        if (s.equals("Lotse"))
        {
            model.changeSelectedMap(model.getLotseMap());
        }
        else if (s.equals("S09l"))
        {
            model.changeSelectedMap(model.getS09lMap());
        }
    }

    @Override
    public void mouseDragged(final MouseEvent e)
    {
        updateMousePosition(e);
        if (mouseIn)
        {
            model.moveMarked(e.getX(), e.getY());
        }

    }

    @Override
    public void mouseMoved(final MouseEvent e)
    {
        updateMousePosition(e);
        if (mouseIn)
        {
            model.mark(e.getX(), e.getY());
        }

    }

    private void updateMousePosition(final MouseEvent e)
    {
        model.setMouseX(e.getX());
        model.setMouseY(e.getY());

    }

    @Override
    public void mouseClicked(final MouseEvent e)
    {
        // if (e.getButton() == MouseEvent.BUTTON1)
        // {
        // // System.out.println("LeftMouse: " + model.getMouseX() + " " +
        // // model.getMouseY());
        //
        // final double[][] m = model.getMapTransform();
        // final double[] v =
        // { model.getMouseX(), model.getMouseY(), 1.0 };
        // final double[] result = Matrix.matMult(m, v);
        // model.addPoint(result);
        //
        // // System.out.println("Add Point: " + result[0] + " " + result[1]);
        //
        // }
        // else if (e.getButton() == MouseEvent.BUTTON3)
        // {
        // // TODO: Punkt löschen
        // // System.out.println("RightMouse");
        // // model.removePoint(0);
        // // System.out.println("Remove Point");
        // }
    }

    @Override
    public void mouseEntered(final MouseEvent e)
    {
        mouseIn = true;
        System.out.println(mouseIn);

    }

    @Override
    public void mouseExited(final MouseEvent e)
    {
        mouseIn = false;
        System.out.println(mouseIn);

    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(final MouseEvent e)
    {
        if (mouseIn)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                model.addPoint(new Punkt(e.getX(), e.getY()));
            }
            else if (e.getButton() == MouseEvent.BUTTON3)
            {
                model.removeMarkedPoint();

            }
        }

    }
}
