package gui.plusminus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handler2 implements ActionListener
{
    private final PlusMinus pm;

    private final boolean b; // true = increment() : false = decrement()

    public Handler2(final PlusMinus pm, final boolean b)
    {
        this.pm = pm;
        this.b = b;

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        if (b)
        {
            pm.increment();
        }
        else
        {
            pm.decrement();
        }

    }
}
