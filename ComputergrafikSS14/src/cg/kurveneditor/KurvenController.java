package cg.kurveneditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import cg.punkteditor.Punktliste;

public class KurvenController implements ActionListener {

    private final Punktliste punkte;
    Zeichenflaeche zf;

    public KurvenController(Zeichenflaeche zf) {
        this.punkte = zf.getPunkte();
        this.zf = zf;
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

        if (s.equals("Eingepannte - HS")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            if (jcb.isSelected()) {
                zf.drawEingespannteHS(true);
                System.out.println("Eingepannte - HS zeichen");
            } else {
                zf.drawEingespannteHS(false);
                System.out.println("Eingepannte - HS nicht zeichnen");
            }
        }

        if (s.equals("Natürliche - HS")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            if (jcb.isSelected()) {
                zf.drawNaturHS(true);
                System.out.println("Natürliche - HS zeichen");
            } else {
                zf.drawNaturHS(false);
                System.out.println("Natürliche - HS nicht zeichnen");
            }
        }

        if (s.equals("Geschlossene - HS")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            if (jcb.isSelected()) {
                zf.drawGeschlossenHS(true);
                System.out.println("Geschlossene - HS zeichen");
            } else {
                zf.drawGeschlossenHS(false);
                System.out.println("Geschlossene - HS nicht zeichnen");
            }
        }

        if (s.equals("Parabol - HS")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            if (jcb.isSelected()) {
                zf.drawParabolHS(true);
                System.out.println("Parabol - HS zeichen");
            } else {
                zf.drawParabolHS(false);
                System.out.println("Parabol - HS nicht zeichnen");
            }
        }

    }
}
