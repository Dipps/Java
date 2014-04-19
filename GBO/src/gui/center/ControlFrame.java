package gui.center;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlFrame extends JFrame implements ActionListener
{

    public ControlFrame()
    {
        // Button zum Frame zentrieren
        final JButton b = new JButton("Zentrieren!");
        b.setName("control");
        add(b);
        b.addActionListener(this);

        // Button zum anzeigen aller Fenster
        final JButton show = new JButton("Alle Anzeigen!");
        add(show);
        final ActionListener vT = new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                CenterFrame.setAllVisible(true);
            }
        };
        show.addActionListener(vT);

        // Button zum verstecken aller Fenster
        final JButton hide = new JButton("Alle Verstecken!");
        add(hide);
        final ActionListener vF = new ActionListener()
        {

            @Override
            public void actionPerformed(final ActionEvent e)
            {
                CenterFrame.setAllVisible(false);

            }
        };
        hide.addActionListener(vF);

        // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 0));
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        CenterFrame.setAllIntoCenter();
    }
}
