package gui.mvc.bit;

import java.util.ArrayList;

public class BitModel implements IBitModel
{
    // nötige Attribute
    private final ArrayList<IBitModelListener> listeners;

    private final boolean[] bitmodel;

    public BitModel(final int length)
    {
        this.bitmodel = new boolean[length];
        listeners = new ArrayList<>();
    }

    @Override
    public int getLength()
    {
        return bitmodel.length;
    }

    @Override
    public boolean get(final int index)
    {
        // TODO Ausnahme falls index >= Länge
        return bitmodel[index];
    }

    @Override
    public void set(final int index, final boolean b)
    {
        // TODO Ausnahme falls index >= länge
        bitmodel[index] = b;

        // fireModelChanged, kommt nur einmal vor,
        // also direkt hier implementiert
        for (final IBitModelListener l : listeners)
        {
            l.modelChanged();
        }

    }

    @Override
    public void addModelListener(final IBitModelListener l)
    {
        listeners.add(l);
    }

    @Override
    public void removeModelListener(final IBitModelListener l)
    {
        listeners.remove(l);

    }
}
