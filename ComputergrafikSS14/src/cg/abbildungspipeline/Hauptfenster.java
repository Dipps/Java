package cg.abbildungspipeline;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class Hauptfenster extends JFrame {

    private final AbbModel model = new AbbModel();
    private final AbbController controller = new AbbController(model);

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuDatei = new JMenu("Datei");
    private final JMenu menuAnsicht = new JMenu("Ansicht");
    private final JMenuItem miBeenden = new JMenuItem("Beenden");

    private final GraphicsView gv = new GraphicsView(model);
    private final JScrollPane sp = new JScrollPane(gv);

    private final SliderView sliderView = new SliderView(model, controller);
    private final MatrixPanelView matrixView = new MatrixPanelView(model,
            controller);
    private final SliderAnsichtsView sav = new SliderAnsichtsView(model,
            controller);

    public Hauptfenster() {

        super("3D-Transformationen Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model.addAbbModelListener(sliderView);
        model.addAbbModelListener(matrixView);
        model.addAbbModelListener(gv);
        model.addAbbModelListener(sav);

        menuBar.add(menuDatei);
        menuBar.add(menuAnsicht);
        menuDatei.add(miBeenden);
        menuAnsicht.add(sav);

        miBeenden.addActionListener(controller);

        add(menuBar, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);

        add(sliderView, BorderLayout.SOUTH);
        add(matrixView, BorderLayout.EAST);

        setLocation(25, 25);
        // pack();
        setSize(1024, 800);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Hauptfenster();
    }
}
