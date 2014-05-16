package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

public class LEDFenster extends JLabel implements MouseMotionListener,
        MouseWheelListener {

    private final Leuchtdiode diode = new Leuchtdiode(100, 150, 30, Color.RED);
    private final Segment segment = new Segment(100, 100, 15, 30, Color.RED);

    public LEDFenster() {
        super();
        addMouseMotionListener(this);
        addMouseWheelListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // diode.draw(g);
        segment.draw(g);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // System.out.println(e.getModifiersEx());
        // System.out.println(MouseWheelEvent.SHIFT_DOWN_MASK);

        segment.setAnzahl(segment.getAnzahl() + e.getWheelRotation());
        repaint();

        // Shift + Mausrad zum Skallieren
        if (e.getModifiersEx() == InputEvent.SHIFT_DOWN_MASK) {
            double scaleFactor = 0.2 * e.getPreciseWheelRotation();
            diode.scale(scaleFactor);
            repaint();
        }

        if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
            diode.rotate(20);
            repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        diode.setPosition(e.getX(), e.getY());
        segment.setPosition(e.getX(), e.getY());
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
