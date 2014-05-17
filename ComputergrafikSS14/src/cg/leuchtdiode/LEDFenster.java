package cg.leuchtdiode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

public class LEDFenster extends JLabel implements MouseMotionListener,
        MouseWheelListener, MouseListener {

    private final Leuchtdiode diode = new Leuchtdiode(100, 150, 30, Color.RED);
    private final Segment segment = new Segment(150, 150, 15, 15, Color.RED);
    private final SiebenSegmentAnzeige siebenS = new SiebenSegmentAnzeige(150,
            150, 90, 10, 10, 0);

    public LEDFenster() {
        super();
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // diode.draw(g);
        // segment.draw(g);
        siebenS.draw(g);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // System.out.println(e.getModifiersEx());
        // System.out.println(MouseWheelEvent.SHIFT_DOWN_MASK);

        // segment.setAnzahl(segment.getAnzahl() + e.getWheelRotation());
        // repaint();

        // Shift + Mausrad zum Skallieren
        if (e.getModifiersEx() == InputEvent.SHIFT_DOWN_MASK) {
            double scaleFactor = 0.2 * e.getPreciseWheelRotation();
            diode.scale(scaleFactor);
            segment.setAnzahl(segment.getAnzahl() + e.getWheelRotation());
            siebenS.scaleHeight(e.getWheelRotation());
            repaint();
        }

        if (e.getModifiersEx() == InputEvent.ALT_DOWN_MASK) {
            siebenS.scaleWidth(e.getWheelRotation());
            repaint();
        }

        if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
            diode.rotate(5 * e.getWheelRotation());
            segment.rotate(5 * e.getWheelRotation());
            siebenS.setSchraegWinkel(siebenS.getSchraegWinkel()
                    + (1 * e.getWheelRotation()));
            repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        diode.setPosition(e.getX(), e.getY());
        segment.setPosition(e.getX(), e.getY());
        siebenS.setPosition(e.getX(), e.getY());
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            segment.setColor(Color.GREEN);
            repaint();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            segment.setColor(Color.RED);
            repaint();
        }

    }

}
