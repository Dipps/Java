package gui.mvc.bit;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BitViewController implements ItemListener
{

    // Attribute: bitte ergänzen
    private final IBitModel bm;

    private final int index;

    // Konstruktor: bitte ergänzen
    public BitViewController(final IBitModel bm, final int index)
    {
        this.bm = bm;
        this.index = index;

    }

    @Override
    public void itemStateChanged(final ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            bm.set(index, true);
            System.out.println("select" + " index: " + index + " Wert: " + bm.get(index));

        }
        else if (e.getStateChange() == ItemEvent.DESELECTED)
        {
            bm.set(index, false);
            System.out.println("select" + " index: " + index + " Wert: " + bm.get(index));
        }

    }
}
