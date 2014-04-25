package cg.seekarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MapController implements ActionListener, MouseMotionListener {

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

}
