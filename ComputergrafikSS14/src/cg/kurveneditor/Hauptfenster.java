package cg.kurveneditor;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class Hauptfenster extends JFrame {

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuDatei = new JMenu("Datei");
    private final JMenu menuOption = new JMenu("Optionen");
    private final JMenuItem miBeenden = new JMenuItem("Beenden");

    private final JCheckBox cbDrawLine = new JCheckBox("Linien zeichen");

    private final StatusAnzeige sa = new StatusAnzeige();

    private final Zeichenflaeche zf = new Zeichenflaeche(sa);
    private final JScrollPane sp = new JScrollPane(zf);

    private final KurvenController controller = new KurvenController(
            zf.getPunkte());

    public Hauptfenster() {
        super("Kurveneditor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar.add(menuDatei);
        menuBar.add(menuOption);
        menuDatei.add(miBeenden);
        menuOption.add(cbDrawLine);

        miBeenden.addActionListener(controller);
        cbDrawLine.addActionListener(controller);

        add(menuBar, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(sa, BorderLayout.SOUTH);

        setLocation(100, 100);
        setSize(800, 800);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Hauptfenster();
    }

}
