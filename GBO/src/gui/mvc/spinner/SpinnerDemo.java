package gui.mvc.spinner;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.WindowConstants;

public class SpinnerDemo extends JFrame
{

    public SpinnerDemo(final String title, final long min, final long max, final long inc)
    {
        super(title);

        final JLabel l = new JLabel("neuer Wert: " + min);
        l.setName("infoLabel");

        final MyChangeListener mcl = new MyChangeListener(l);

        final MySpinnerModel model = new MySpinnerModel(min, max, inc);
        model.addChangeListener(mcl);
        final JSpinner spinner = new JSpinner(model);
        spinner.setName("spinner");
        // spinner.addChangeListener(mcl);

        add(spinner);
        add(l);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        pack();
        setVisible(true);

    }

    public static void main(final String[] args)
    {
        new SpinnerDemo("Spinnerdemo", -10, 10, 4);

    }

}
