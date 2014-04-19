package gui.state.clock;

import java.util.ArrayList;

public class Clock
{
    private int hours;

    private int min;

    private State state;

    private final ArrayList<ClockListener> listeners;

    public Clock()
    {
        state = new NormalState(this);
        hours = 0;
        min = 0;

        listeners = new ArrayList<>();
    }

    public void setState(final State state)
    {
        this.state = state;
    }

    public void incrementHours()
    {
        hours++;
        hours %= 24;
        fireModelChanged();
    }

    public void decrementHours()
    {
        hours--;
        if (hours < 0)
        {
            hours = 23;
        }
        fireModelChanged();
    }

    public void incrementMinutes()
    {
        min++;
        min %= 60;
        fireModelChanged();
    }

    public void decrementMinutes()
    {
        min--;
        if (min < 0)
        {
            min = 59;
        }
        fireModelChanged();
    }

    public void set()
    {
        state.set();
    }

    public void plus()
    {
        state.plus();
    }

    public void minus()
    {
        state.minus();
    }

    public void addClockListener(final ClockListener cl)
    {
        listeners.add(cl);
    }

    public void removeClockListener(final ClockListener cl)
    {
        listeners.remove(cl);
    }

    public void fireModelChanged()
    {
        for (final ClockListener l : listeners)
        {
            l.update(hours, min);
        }
    }
}
