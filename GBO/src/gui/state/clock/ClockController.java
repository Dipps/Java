package gui.state.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockController implements ActionListener
{
    private final Clock model;

    public ClockController(final Clock model)
    {
        this.model = model;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("Set"))
        {
            model.set();
        }
        else if (s.equals("+"))
        {
            model.plus();
        }
        else if (s.equals("-"))
        {
            model.minus();
        }
        else
        {
            throw new RuntimeException("Button nicht vorhanden");
        }

    }

}
