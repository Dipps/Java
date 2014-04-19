package gui.colorChooser;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColorChooser extends JFrame implements ActionListener
{
    private final JButton fG;

    private final JButton bG;

    private final JLabel l;

    public ColorChooser(final String title)
    {
        super(title);
        fG = new JButton("Dialog für Vordergrundfarbe");
        fG.addActionListener(this);

        bG = new JButton("Dialog für Hintergrundfarbe");
        bG.addActionListener(this);

        l = new JLabel("Beispieltext");
        l.setOpaque(true);

        add(fG);
        add(bG);
        add(l);

        setLayout(new GridLayout(3, 0));
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("Dialog für Vordergrundfarbe"))
        {
            final Color c = JColorChooser.showDialog(null, "Vordergrundfarbe", l.getForeground());
            l.setForeground(c);
        }
        else if (s.equals("Dialog für Hintergrundfarbe"))
        {
            final Color c = JColorChooser.showDialog(null, "Hintergrundfarbe", l.getBackground());
            l.setBackground(c);
        }
        else
        {
            throw new RuntimeException("Kein Button mit dem Namen vorhanden!");
        }

    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        new ColorChooser("DialogTest");

    }
}
