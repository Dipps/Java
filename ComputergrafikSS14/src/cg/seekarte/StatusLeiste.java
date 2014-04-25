package cg.seekarte;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusLeiste extends JPanel implements IMapModelListener {
    private final JLabel lBx = new JLabel("Bx: ");

    private final JLabel lBy = new JLabel("By: ");

    private final JLabel lMapx = new JLabel("Kx: ");

    private final JLabel lMapy = new JLabel("Ky: ");

    public StatusLeiste(MapModel model) {
        setLayout(new GridLayout(1, 4));

        add(lBx);
        add(lBy);

        add(lMapx);
        add(lMapy);

    }

    @Override
    public void mapModelChanged(MapModel model) {
        lBx.setText("Bx: " + model.getMouseX());
        lBy.setText("By: " + model.getMouseY());

        double[] mapCoords = model.getMapCoords();
        lMapx.setText("Kx: " + formateCoords(mapCoords[0]));
        lMapy.setText("Ky: " + formateCoords(mapCoords[1]));

    }

    public String formateCoords(double d) {
        String a = "" + (int) d;
        String b = "" + (int) ((d * 60) % 60);
        String result = a + "° " + b + "'";
        return result;

    }
}
