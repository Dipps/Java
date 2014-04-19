package gui.mvc.plusminus;

import java.util.ArrayList;

/**
 * Modellkomponente
 */
public class PlusMinusModel
{
    // ggf. noetige Attribute ergeanzen
    private final ArrayList<IPlusMinusListener> listeners;

    private final int minimum;

    private final int maximum;

    private final int increment;

    private int value;

    /**
     * Initialisiert ein neues PlusMinusModel-Objekt mit dem Minimum, dem
     * Maximum und der Schrittweite. Der Zaehlwert, den dieses Modell
     * repraesentiert, wird mit dem Minimum initialisiert.
     * 
     * @param minimum
     *            Fuer den Zaehlerwert zu verwendendes Minimum
     * @param maximum
     *            Fuer den Zeahlerwert zu verwendendes Maximum
     * @param increment
     *            Fuer den Zeahlerwert zu verwendende Schrittweite
     */
    public PlusMinusModel(final int minimum, final int maximum, final int increment)
    {

        if (minimum > maximum || increment <= 0)
        {
            throw new IllegalArgumentException("minimum > maximum oder increment <= 0");
        }
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
        this.value = minimum;
        listeners = new ArrayList<>();
    }

    /**
     * Inkrementiert den aktuellen Zaehlerwert. Beim Inkrementieren ueber das im
     * Konstruktor uebergebene Maximum wird eine Ausnahme ausgeloest.
     */
    public void increment()
    {
        if (canIncrement())
        {
            value += increment;
            fireModelChanged();
        }
        else
        {
            throw new CounterModificationNotPossibleException("can't increment");
        }

    }

    /**
     * Dekrementiert den aktuellen Zaehlerwert. Beim Dekrementieren unter das im
     * Konstruktor uebergebene Minimum wird eine Ausnahme ausgeloest.
     */
    public void decrement()
    {

        if (canDecrement())
        {
            value -= increment;
            fireModelChanged();
        }
        else
        {
            throw new CounterModificationNotPossibleException("can't decrement");
        }

    }

    /**
     * Liefert den aktuellen Zaehlerwert zurueck.
     * 
     * @return int
     */
    public int getCounter()
    {
        return value;
    }

    /**
     * Liefert zurueck, ob der Wert noch erhoeht werden kann.
     * 
     * @return boolean
     */
    public boolean canIncrement()
    {
        return value + increment > maximum ? false : true;
    }

    /**
     * Liefert zurueck, ob der Wert noch erniedrigt werden kann.
     * 
     * @return boolean
     */
    public boolean canDecrement()
    {
        return value - increment < minimum ? false : true;
    }

    /**
     * Meldet den uebergebenen IPlusMinusListener an diesem Modell an.
     * 
     * @param l
     *            Anzumeldender IPlusMinusListener
     */
    public void addPlusMinusModelListener(final IPlusMinusListener l)
    {
        listeners.add(l);
    }

    /**
     * Meldet den uebergebenen IPlusMinusListener an diesem Modell ab.
     * 
     * @param l
     *            Abzumeldender IPlusMinusListener
     */
    public void removePlusMinusModelListener(final IPlusMinusListener l)
    {
        listeners.remove(l);
    }

    public void fireModelChanged()
    {
        for (final IPlusMinusListener l : listeners)
        {
            l.plusMinusModelChanged(this);
        }
    }
}
