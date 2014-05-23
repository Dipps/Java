package cg.leuchtdiode;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class Hauptfenster extends JFrame implements ActionListener {

    private final LEDFenster ledFenster = new LEDFenster();
    private final JScrollPane sp = new JScrollPane(ledFenster);
    private final NCView ncv = new NCView(ledFenster.getSiebenSegmentAnzeige(),
            this);

    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu menuDatei = new JMenu("Datei");

    private final JMenuItem miCreatePNG = new JMenuItem("Erstelle PNG");

    private final JMenuItem miCreateNC = new JMenuItem("Erstelle NC-Programm");

    public Hauptfenster() {
        this("LED - Fenster");
    }

    public Hauptfenster(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuDatei.add(miCreatePNG);
        menuDatei.add(miCreateNC);
        menuBar.add(menuDatei);

        miCreatePNG.addActionListener(this);
        miCreateNC.addActionListener(this);

        add(menuBar, BorderLayout.SOUTH);
        add(sp, BorderLayout.CENTER);

        setLocation(10, 10);
        setSize(800, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Hauptfenster();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Erstelle PNG")) {
            System.out.println("Erstelle PNG");
            ncv.writeImage();
        } else if (s.equals("Erstelle NC-Programm")) {
            System.out.println("Erstelle NC-Programm");
            ncv.writeNCFile();
        }
    }
}
