package gui.mvc.spinner;

import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyChangeListener implements ChangeListener
{
    private final JLabel l;

    public MyChangeListener(final JLabel l)
    {
        this.l = l;
    }

    @Override
    public void stateChanged(final ChangeEvent e)
    {
        l.setText("neuer Wert: " + e.getSource());
    }
}
