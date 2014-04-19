package gui.gridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyGridBagLayout extends JFrame
{

    public MyGridBagLayout(final String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel pane = new JPanel();
        final GridBagLayout gridbag = new GridBagLayout();
        pane.setLayout(gridbag);

        JButton b;

        // für jedes Interaktionselement kann ein GridBagConstraints-
        // Objekt erzeugt werden, um die gewünschte Anordnung genau
        // zu spezifizieren
        GridBagConstraints constraints;

        Insets insets = new Insets(30, 0, 0, 0);
        b = new JButton("Button 1");
        constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 40, 120);
        pane.add(b, constraints);

        insets = new Insets(10, 0, 10, 0);
        b = new JButton("Button 2");
        constraints = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, insets, 0, 0);
        pane.add(b, constraints);

        b = new JButton("Button 3");
        constraints = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
        pane.add(b, constraints);

        b = new JButton("Button 4");
        constraints = new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        pane.add(b, constraints);

        b = new JButton("Button 5");
        constraints = new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        pane.add(b, constraints);

        b = new JButton("Button 6");
        constraints = new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        pane.add(b, constraints);

        b = new JButton("Button 7");
        constraints = new GridBagConstraints(1, 0, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        pane.add(b, constraints);

        add(pane);
        pack();
        setVisible(true);

    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        new MyGridBagLayout("GridBagLayout - Beispiel");

    }

}
