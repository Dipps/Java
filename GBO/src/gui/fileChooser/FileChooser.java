package gui.fileChooser;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FileChooser extends JFrame implements ActionListener
{
    private final JButton open;

    private final JButton save;

    private final JLabel l;

    public FileChooser(final String title)
    {
        super(title);

        open = new JButton("Datei öffnen");
        open.addActionListener(this);

        save = new JButton("Datei speichern");
        save.addActionListener(this);

        l = new JLabel();

        add(open);
        add(save);
        add(l);

        setLayout(new GridLayout(3, 0));
        setSize(200, 200);
        // pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();
        final JFileChooser jfc = new JFileChooser();

        if (s.equals("Datei öffnen"))
        {
            // final JFileChooser jfc = new JFileChooser();
            final int rueckgabeWert = jfc.showOpenDialog(null);

            if (rueckgabeWert == JFileChooser.APPROVE_OPTION)
            {
                l.setText("open: " + jfc.getSelectedFile().getAbsolutePath());
            }
            else
            {
                l.setText("open: Abbruch der Aktion");
            }

        }
        else if (s.equals("Datei speichern"))
        {
            // final JFileChooser jfc = new JFileChooser();
            final int rueckgabeWert = jfc.showSaveDialog(null);

            if (rueckgabeWert == JFileChooser.APPROVE_OPTION)
            {
                l.setText("speichern: " + jfc.getSelectedFile().getAbsolutePath());
            }
            else
            {
                l.setText("speichern: Abbruch der Aktion");
            }
        }
        else
        {
            throw new RuntimeException("Kein JButton mit dem Namen!");
        }

    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        new FileChooser("Dateiauswahltest");
        new FileChooser("Dateiauswahltest");
        new FileChooser("Dateiauswahltest");
        new FileChooser("Dateiauswahltest");

    }

}
