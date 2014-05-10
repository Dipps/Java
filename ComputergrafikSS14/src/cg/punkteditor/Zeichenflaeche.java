package cg.punkteditor;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class Zeichenflaeche extends JLabel implements MouseMotionListener,
        MouseListener {

    private final Punktliste punkte;
    private boolean mouseIn = false;

    public Zeichenflaeche() {
        punkte = new Punktliste();
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        punkte.draw(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseIn) {
            punkte.moveMarked(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mouseIn) {
            punkte.mark(e.getX(), e.getY());
            repaint();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseIn = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseIn = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseIn) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                punkte.add(new Punkt(e.getX(), e.getY()));
                repaint();
                System.out.println("B1");
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                punkte.removeMarked();
                System.out.println("B3");
            }
        }

    }
}
