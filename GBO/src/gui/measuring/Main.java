package gui.measuring;

public class Main
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        // Messzentrum erstellen
        final MeasuringCenter mc = new MeasuringCenter();

        // Messinstrumente anlegen
        final MeasuringDevice md1 = new MeasuringDevice(mc, 1);
        final MeasuringDevice md2 = new MeasuringDevice(mc, 2);

        // AlarmListener
        final AlarmListener al = new AlarmListener(50, 100);
        mc.addMeasuringListener(al);

        md1.measure(49); // Meldung
        md1.measure(101); // Meldung
        md1.measure(75); // Keine Meldung

        md2.measure(49); // Meldung
        md2.measure(101); // Meldung
        md2.measure(75); // Keine Meldung
    }

}
