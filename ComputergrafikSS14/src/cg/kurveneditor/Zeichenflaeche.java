package cg.kurveneditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import cg.punkteditor.Punkt;
import cg.punkteditor.Punktliste;

public class Zeichenflaeche extends JLabel implements MouseListener,
        MouseMotionListener {

    private final Punktliste punkte;
    private final StatusAnzeige sa;
    private boolean mouseIn = false;

    public Zeichenflaeche(StatusAnzeige sa) {
        punkte = new Punktliste();
        punkte.setDrawLine(false);
        punkte.setLineColor(Color.GREEN);
        this.sa = sa;
        sa.setPunkte(punkte.getSize());
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        punkte.draw(g);

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
                sa.setPunkte(punkte.getSize());
                repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                punkte.removeMarked();
                sa.setPunkte(punkte.getSize());
                repaint();
            }
        }

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

    public Punktliste getPunkte() {
        return punkte;
    }
}
