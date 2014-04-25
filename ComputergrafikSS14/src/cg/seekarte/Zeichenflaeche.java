package cg.seekarte;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Zeichenflaeche extends JLabel implements IMapModelListener {

    public Zeichenflaeche(MapModel model) {
        super(model.getSelectedMapIcon());
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.TOP);
    }

    @Override
    public void mapModelChanged(MapModel model) {
        setIcon(model.getSelectedMapIcon());

    }
}
