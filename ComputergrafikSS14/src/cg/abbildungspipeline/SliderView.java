package cg.abbildungspipeline;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderView extends JPanel implements IAbbListener {

    private final JButton lFactor;
    private final JButton lAlpha;
    private final JButton lBeta;
    private final JButton lGamma;

    private final JSlider sFactor;
    private final JSlider sAlpha;
    private final JSlider sBeta;
    private final JSlider sGamma;

    public SliderView(AbbModel model, AbbController controller) {
        setLayout(new GridLayout(2, 0));

        lFactor = new JButton("Faktor = " + model.getFactor());
        lAlpha = new JButton("Alpha = " + model.getAlpha());
        lBeta = new JButton("Beta = " + model.getBeta());
        lGamma = new JButton("Gamma = " + model.getGamma());

        sFactor = new JSlider(0, 50, (int) (model.getFactor() * 10));
        sAlpha = new JSlider(0, 360, (int) model.getAlpha());
        sBeta = new JSlider(0, 360, (int) model.getBeta());
        sGamma = new JSlider(0, 360, (int) model.getGamma());

        sFactor.addChangeListener(controller);
        sAlpha.addChangeListener(controller);
        sBeta.addChangeListener(controller);
        sGamma.addChangeListener(controller);

        add(lFactor);
        add(lAlpha);
        add(lBeta);
        add(lGamma);
        setSliderLabels();
        add(sFactor);
        add(sAlpha);
        add(sBeta);
        add(sGamma);

    }

    private void setSliderLabels() {
        sFactor.setMinorTickSpacing(1);
        sFactor.setMajorTickSpacing(10);
        sFactor.setPaintTicks(true);
        sFactor.setPaintLabels(true);
        sFactor.setName("sFactor");

        sAlpha.setMinorTickSpacing(15);
        sAlpha.setMajorTickSpacing(60);
        sAlpha.setPaintTicks(true);
        sAlpha.setPaintLabels(true);
        sAlpha.setName("sAlpha");

        sBeta.setMinorTickSpacing(15);
        sBeta.setMajorTickSpacing(60);
        sBeta.setPaintTicks(true);
        sBeta.setPaintLabels(true);
        sBeta.setName("sBeta");

        sGamma.setMinorTickSpacing(15);
        sGamma.setMajorTickSpacing(60);
        sGamma.setPaintTicks(true);
        sGamma.setPaintLabels(true);
        sGamma.setName("sGamma");
    }

    @Override
    public void AbbModelChanged(AbbModel model) {
        lFactor.setText("Faktor = " + model.getFactor());
        lAlpha.setText("Alpha = " + model.getAlpha());
        lBeta.setText("Beta = " + model.getBeta());
        lGamma.setText("Gamma = " + model.getGamma());

        sFactor.setValue((int) (model.getFactor() * 10));
        sAlpha.setValue((int) model.getAlpha());
        sBeta.setValue((int) model.getBeta());
        sGamma.setValue((int) model.getGamma());

    }
}
