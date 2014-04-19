package gui.mvc.spinner;

import java.util.ArrayList;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MySpinnerModel implements SpinnerModel
{
    /**
     * Aktueller Wert.
     */
    private long value;

    /**
     * Minimum.
     */
    private final long min;

    /**
     * Maximum.
     */
    private final long max;

    /**
     * Wert, um den der aktuelle Wert jeweils erhöht bzw. erniedrigt wird.
     */
    private final long increment;

    /**
     * Liste für die Listeners.
     */
    private final ArrayList<ChangeListener> listeners;

    /**
     * Konstruktor. Es soll eine IllegalArgumentException geworfen werden, falls
     * die Parameter unsinnig sind (d.h. min > max oder increment <= 0). Der
     * Startwert soll min sein.
     * 
     * @param min
     *            minimum des JSpinners
     * @param max
     *            maximum des JSpinners
     * @param increment
     *            Wert um den inkrementiert wird
     */
    public MySpinnerModel(final long min, final long max, final long increment)
    {
        if (min > max || increment <= 0)
        {
            throw new IllegalArgumentException("Parameter sind unsinnig");
        }

        this.min = min;
        this.value = min;
        this.max = max;
        this.increment = increment;
        listeners = new ArrayList<>();

    }

    /**
     * Methode getValue. Der aktuelle Wert als Object.
     */
    @Override
    public Object getValue()
    {
        return value; // seit java 5 Autoboxing
        // return Long.valueOf(value);
    }

    /**
     * Methode setValue. Sie können davon ausgehen, dass der Parameter o vom
     * selben Typ ist wie von Ihnen bei getNextValue, getPreviousValue und
     * getValue zurückgeliefert. Es müssen alle angemeldeten Listener
     * benachrichtigt werden.
     */
    @Override
    public void setValue(final Object o)
    {
        value = (long) o; // Autoboxing
        fireModelChanged(o);

    }

    /**
     * Methode getNextValue. Falls es einen nächsten Wert gibt, muss dieser als
     * Object zurückgeliefert werden, sonst null.
     */
    @Override
    public Object getNextValue()
    {
        if (value + increment <= max)
        {
            return value + increment;
        }
        else
        {
            return null;
        }
    }

    /**
     * Methode getPreviousValue. Falls es einen vorigen Wert gibt, muss dieser
     * als Object zurückgeliefert werden, sonst null.
     */
    @Override
    public Object getPreviousValue()
    {
        if (value - increment >= min)
        {
            return value - increment;
        }
        else
        {
            return null;
        }
    }

    /**
     * Registriert einen ChangeListener.
     */
    @Override
    public void addChangeListener(final ChangeListener l)
    {
        listeners.add(l);

    }

    /**
     * Meldet einen ChangeListener ab.
     */
    @Override
    public void removeChangeListener(final ChangeListener l)
    {
        listeners.remove(l);

    }

    /**
     * Benachrichtigt alle ChangeListeners
     * 
     * @param o
     *            Quelle die das Ereignis auslöst.
     */
    public void fireModelChanged(final Object o)
    {
        final ChangeEvent e = new ChangeEvent(o);

        for (final ChangeListener l : listeners)
        {
            l.stateChanged(e);
        }
    }
}
