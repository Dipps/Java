package cg.seekarte;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusLeiste extends JPanel implements MouseMotionListener
{
    private final JLabel lBx = new JLabel("Bx: ");

    private final JLabel lBy = new JLabel("By: ");

    private final JLabel lMapx = new JLabel("Kx");

    private final JLabel lMapy = new JLabel("Ky");

    public StatusLeiste()
    {
        setLayout(new GridLayout(1, 4));

        add(lBx);
        add(lBy);

        add(lMapx);
        add(lMapy);

        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(final MouseEvent e)
    {
        updateMousePosition(e);

    }

    @Override
    public void mouseMoved(final MouseEvent e)
    {
        updateMousePosition(e);

    }

    private void updateMousePosition(final MouseEvent e)
    {
        lBx.setText("Bx: " + e.getX());
        lBy.setText("By: " + e.getY());
        // repaint();
    }

}
