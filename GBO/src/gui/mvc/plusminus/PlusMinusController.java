package gui.mvc.plusminus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusMinusController implements ActionListener
{
    private final PlusMinusModel pmm;

    public PlusMinusController(final PlusMinusModel pmm)
    {
        this.pmm = pmm;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();
        if (s.equals("+"))
        {
            pmm.increment();
        }
        else if (s.equals("-"))
        {
            pmm.decrement();
        }
        else
        {
            throw new IllegalArgumentException("false argument");
        }
    }

}
