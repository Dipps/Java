package cg.abbildungspipeline;

import java.awt.Graphics;

import javax.swing.JLabel;

public class Zeichenflaeche extends JLabel {

    public Zeichenflaeche() {
    }

    @Override
    protected void paintComponent(final Graphics g) {
        g.drawLine(0, 0, 150, 150);
    }
}
