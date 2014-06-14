package cg.abbildungspipeline;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderAnsichtsView extends JPanel implements IAbbListener {

    private final JLabel lAlphaA;
    private final JLabel lBetaA;
    private final JLabel lGammaA;

    private final JSlider sAlphaA;
    private final JSlider sBetaA;
    private final JSlider sGammaA;

    public SliderAnsichtsView(AbbModel model, AbbController controller) {
        setLayout(new GridLayout(3, 0));
        sAlphaA = new JSlider(0, 360, (int) model.getAlphaA());
        sBetaA = new JSlider(0, 360, (int) model.getBetaA());
        sGammaA = new JSlider(0, 360, (int) model.getGammaA());

        lAlphaA = new JLabel("Alpha = " + model.getAlphaA());
        lBetaA = new JLabel("Beta = " + model.getBetaA());
        lGammaA = new JLabel("Gamma = " + model.getGammaA());

        sAlphaA.addChangeListener(controller);
        sBetaA.addChangeListener(controller);
        sGammaA.addChangeListener(controller);

        initSlider();

        add(lAlphaA);
        add(sAlphaA);
        add(lBetaA);
        add(sBetaA);
        add(lGammaA);
        add(sGammaA);
    }

    private void initSlider() {
        sAlphaA.setName("sAlphaA");
        sAlphaA.setMinorTickSpacing(15);
        sAlphaA.setMajorTickSpacing(60);
        sAlphaA.setPaintTicks(true);
        sAlphaA.setPaintLabels(true);

        sBetaA.setName("sBetaA");
        sBetaA.setMinorTickSpacing(15);
        sBetaA.setMajorTickSpacing(60);
        sBetaA.setPaintTicks(true);
        sBetaA.setPaintLabels(true);

        sGammaA.setName("sGammaA");
        sGammaA.setMinorTickSpacing(15);
        sGammaA.setMajorTickSpacing(60);
        sGammaA.setPaintTicks(true);
        sGammaA.setPaintLabels(true);
    }

    @Override
    public void AbbModelChanged(AbbModel model) {
        sAlphaA.setValue((int) model.getAlphaA());
        sBetaA.setValue((int) model.getBetaA());
        sGammaA.setValue((int) model.getGammaA());

        lAlphaA.setText("Alpha = " + model.getAlphaA());
        lBetaA.setText("Beta = " + model.getBetaA());
        lGammaA.setText("Gamma = " + model.getGammaA());
    }

}
