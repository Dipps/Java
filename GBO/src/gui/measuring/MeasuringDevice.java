package gui.measuring;

public class MeasuringDevice
{
    private final MeasuringCenter mc;

    private final int id;

    public MeasuringDevice(final MeasuringCenter mc, final int id)
    {
        this.mc = mc;
        this.id = id;
    }

    public void measure(final double wert)
    {
        mc.reportMeasuredValue(wert, id);
    }
}
