package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class GridBagLayoutExample extends JFrame
{
    public GridBagLayoutExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        contentPane.setLayout(gridbag);

        JButton button;
        GridBagConstraints constraints;
        Insets insets = new Insets(0, 0, 0, 0);

        button = new JButton("Button 1");
        constraints = new GridBagConstraints(0, 0, // gridx, gridy
                                             1, 1, // gridwidth,
                                                   // gridheight
                                             0.0, 0.0, // weightx,
                                                       // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 2");
        constraints = new GridBagConstraints(1, 0, // gridx, gridy
                                             1, 1, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 3");
        constraints = new GridBagConstraints(2, 0, // gridx, gridy
                                             1, 1, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 4");
        constraints = new GridBagConstraints(0, 1, // gridx, gridy
                                             2, 1, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 5");
        constraints = new GridBagConstraints(2, 1, // gridx, gridy
                                             1, 2, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 6");
        constraints = new GridBagConstraints(0, 2, // gridx, gridy
                                             1, 1, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        button = new JButton("Button 7");
        constraints = new GridBagConstraints(1, 2, // gridx, gridy
                                             1, 1, // gridwidth,
                                                    // gridheight
                                             0.0, 0.0, // weightx,
                                                        // weighty
                                             GridBagConstraints.CENTER, // anchor
                                             GridBagConstraints.BOTH,   // fill
                                             //GridBagConstraints.NONE, // fill
                                             //GridBagConstraints.VERTICAL, // fill
                                             //GridBagConstraints.HORIZONTAL, // fill
                                             insets, // insets
                                             0, 0); // ipadx, ipady
        contentPane.add(button, constraints);

        add(contentPane);
    }

    public static void main(String args[])
    {
        GridBagLayoutExample window = new GridBagLayoutExample("GridBagLayout-Beispiel");
        window.pack();
        window.setVisible(true);
    }
}
