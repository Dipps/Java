package cg.abbildungspipeline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AbbController implements ActionListener, ChangeListener {

    private final AbbModel model;

    public AbbController(AbbModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Beenden")) {
            System.out.println("Beenden");
            System.exit(0);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();

        if (js.getName().equals("sFactor")) {
            model.setFactor((double) (js.getValue()) / 10);
        } else if (js.getName().equals("sAlpha")) {
            model.setAlpha(js.getValue());
        } else if (js.getName().equals("sBeta")) {
            model.setBeta(js.getValue());
        } else if (js.getName().equals("sGamma")) {
            model.setGamma(js.getValue());
        }

    }
}
