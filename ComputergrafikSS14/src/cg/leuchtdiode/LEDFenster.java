package cg.leuchtdiode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

public class LEDFenster extends JLabel implements MouseMotionListener,
        MouseWheelListener {

    private final double[][] m = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0 } };
    private final Leuchtdiode diode = new Leuchtdiode(150, 150, m, 50,
            Color.RED);

    public LEDFenster() {
        super();
        addMouseMotionListener(this);
        addMouseWheelListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        diode.draw(g);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f));
        g.drawLine(10, 10, diode.getX(), diode.getY());

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        diode.setX(e.getX());
        diode.setY(e.getY());
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
