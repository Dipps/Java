package gui.measuring;

public class AlarmListener implements MeasuringListener
{
    private final double min;

    private final double max;

    public AlarmListener(final double min, final double max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public void valueMeasured(final double wert, final int id)
    {
        if (wert < min || wert > max)
        {
            System.out.println("Ger‰t: " + id + " meldet Messwert: " + wert + ", der auﬂerhalb des Intervalls [" + min + "," + max + "] liegt.");
        }

    }

}
