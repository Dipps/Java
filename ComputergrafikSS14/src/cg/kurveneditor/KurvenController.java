package cg.kurveneditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import cg.punkteditor.Punktliste;

public class KurvenController implements ActionListener {

    private final Punktliste punkte;

    public KurvenController(Punktliste p) {
        this.punkte = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Beenden")) {
            System.out.println("Beenden");
            System.exit(0);
        }

        if (s.equals("Linien zeichen")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            if (jcb.isSelected()) {
                punkte.setDrawLine(true);
                System.out.println("Linien zeichen");
            } else {
                punkte.setDrawLine(false);
                System.out.println("Linien nicht zeichnen");
            }
        }
    }
}
