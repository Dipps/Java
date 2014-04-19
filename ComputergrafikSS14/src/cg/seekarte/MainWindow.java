package cg.seekarte;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame implements ActionListener
{

    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu menu = new JMenu("Datei");

    private final JMenuItem miKarte01 = new JMenuItem("Lotse1");

    private final JMenuItem miKarte02 = new JMenuItem("S9l");

    private final JLabel karte;

    private final StatusLeiste sl = new StatusLeiste();

    public MainWindow()
    {
        this("Seekarte");
    }

    public MainWindow(final String title)
    {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menü erstellen
        menu.add(miKarte01);
        menu.add(miKarte02);
        menuBar.add(menu);
        add(menuBar, BorderLayout.NORTH);

        // karte initalisieren
        karte = new JLabel(new ImageIcon("res/Seekarte/lotse1.jpg"));
        final JScrollPane sp = new JScrollPane(karte);
        add(sp, BorderLayout.CENTER);

        // Statusleiste hinzufügen
        add(sl, BorderLayout.SOUTH);
        karte.addMouseMotionListener(sl);

        // Listener anmelden
        miKarte01.addActionListener(this);
        miKarte02.addActionListener(this);

        setVisible(true);
        setSize(400, 400);
        setLocation(50, 50);
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("Lotse1"))
        {
            karte.setIcon(new ImageIcon("res/Seekarte/lotse1.jpg"));
        }
        else if (s.equals("S9l"))
        {
            karte.setIcon(new ImageIcon("res/Seekarte/s09l.jpg"));
        }

    }

    public static void main(final String[] args)
    {
        new MainWindow();

    }

}