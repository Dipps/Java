package gui.plusminus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handler implements ActionListener
{
    private final PlusMinus pm;

    public Handler(final PlusMinus pm)
    {
        this.pm = pm;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();
        if (s.equals("+"))
        {
            pm.increment();
            // System.out.println(s);
        }
        else if (s.equals("-"))
        {
            pm.decrement();
            // System.out.println(s);
        }

    }
}
