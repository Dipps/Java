package gui.land;

public class Land
{
    private final String name;

    private final String hauptstadt;

    private final long einwohner;

    private final long flaeche;

    public Land(final String name, final String hauptstadt, final long einwohner, final long flaeche)
    {
        this.name = name;
        this.hauptstadt = hauptstadt;
        this.einwohner = einwohner;
        this.flaeche = flaeche;
    }

    public String getName()
    {
        return name;
    }

    public String getHauptstadt()
    {
        return hauptstadt;
    }

    public long getEinwohner()
    {
        return einwohner;
    }

    public long getFlaeche()
    {
        return flaeche;
    }

    // Methode zu ergänzen (welche und wie?)
    // TODO
    public long getDichte()
    {
        // Math.round rundet double und gibt long wert zurück
        // einwohner auf double casten um die nachkommastellen nicht zu
        // verlieren
        return Math.round((double) einwohner / flaeche);

    }

    @Override
    public String toString()
    {
        return getName();
    }
}
