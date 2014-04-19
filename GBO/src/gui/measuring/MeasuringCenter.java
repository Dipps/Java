package gui.measuring;

import java.util.ArrayList;

public class MeasuringCenter
{
    private final ArrayList<MeasuringListener> list;

    public MeasuringCenter()
    {
        list = new ArrayList<MeasuringListener>();

    }

    public void reportMeasuredValue(final double wert, final int id)
    {
        for (final MeasuringListener mL : list)
        {
            mL.valueMeasured(wert, id);
        }

    }

    public void addMeasuringListener(final MeasuringListener mL)
    {
        list.add(mL);
    }
}
