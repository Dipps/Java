package gui.composite;

/**
 * Klasse für primitive Elemente.
 * 
 * toString Methode entfernt
 * 
 * @author Rainer Oechsle
 * 
 */
public class PrimitiveElement extends Component
{
    private String description;

    /**
     * Konstruktor.
     * 
     * @param name
     *            Name
     * @param weight
     *            Gewicht
     * @param description
     *            Beschreibung
     */
    public PrimitiveElement(final String name, final int weight, final String description)
    {
        super(name, weight);
        this.description = description;
    }

    /**
     * Setzt die Beschreibung für dieses Objekt.
     * 
     * @param description
     *            neue Beschreibung
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * Liefert die Beschreibung dieses Objekts.
     * 
     * @return Beschreibung
     */
    public String getDescription()
    {
        return description;
    }
}
