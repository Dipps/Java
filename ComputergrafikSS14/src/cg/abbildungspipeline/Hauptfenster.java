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
    private final JMenuItem miBeenden = new JMenuItem("Beenden");

    private final Zeichenflaeche zf = new Zeichenflaeche();
    private final JScrollPane sp = new JScrollPane(zf);

    private final SliderView sliderView = new SliderView(model, controller);
    private final MatrixPanelView matrixView = new MatrixPanelView(model,
            controller);

    public Hauptfenster() {

        super("3D-Transformationen Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model.addAbbModelListener(sliderView);
        model.addAbbModelListener(matrixView);

        menuBar.add(menuDatei);
        menuDatei.add(miBeenden);

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
