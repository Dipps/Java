package cg.punkteditor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class PunktFrame extends JFrame {

    private final Zeichenflaeche zf = new Zeichenflaeche();
    private final JScrollPane sp = new JScrollPane(zf);

    public PunktFrame() {
        super("Punkte Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(sp, BorderLayout.CENTER);

        setLocation(200, 200);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PunktFrame();
    }
}
