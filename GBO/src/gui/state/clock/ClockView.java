package gui.state.clock;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ClockView extends JLabel implements ClockListener
{
    public ClockView()
    {
        super("00:00");
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Helvetica", Font.BOLD, 46));
        setName("view");
    }

    @Override
    public void update(final int hours, final int minutes)
    {
        final String h;
        final String m;

        if (hours < 10)
        {
            h = "0" + hours;
        }
        else
        {
            h = "" + hours;
        }

        if (minutes < 10)
        {
            m = "0" + minutes;
        }
        else
        {
            m = "" + minutes;
        }

        setText(h + ":" + m);

    }
}
