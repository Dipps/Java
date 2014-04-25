package cg.seekarte;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class Hauptfenster extends JFrame {

    MapModel model = new MapModel();

    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu menuKarte = new JMenu("Karte");

    private final JMenuItem miLotse = new JMenuItem("Lotse");

    private final JMenuItem miS09l = new JMenuItem("S09l");

    private final Zeichenflaeche zf = new Zeichenflaeche(model);

    private final JScrollPane sp = new JScrollPane(zf);

    private final StatusLeiste sl = new StatusLeiste(model);

    public Hauptfenster() {
        this("Swing-Test");
    }

    public Hauptfenster(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuKarte.add(miLotse);
        menuKarte.add(miS09l);
        menuBar.add(menuKarte);

        add(menuBar, BorderLayout.NORTH);

        MapController controller = new MapController(model);
        miLotse.addActionListener(controller);
        miS09l.addActionListener(controller);

        model.addMapModelListener(sl);
        model.addMapModelListener(zf);

        add(sp, BorderLayout.CENTER);
        zf.addMouseMotionListener(controller);

        add(sl, BorderLayout.SOUTH);

        setLocation(200, 200);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Hauptfenster();

    }

}
