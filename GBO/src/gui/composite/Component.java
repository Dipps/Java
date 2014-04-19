package gui.composite;

/**
 * Abstrakte Basisklasse aller Elemente. Aus dieser Klasse werden
 * PrimitiveElement und Container abgeleitet.
 * 
 * toString Methode modifiziert
 * 
 * @author Rainer Oechsle
 * 
 */
public abstract class Component
{
    /**
     * Referenz auf den Container, in der das jeweilige Element enthalten ist
     * ("Erster" Vorfahre).
     */
    protected Container pred;

    protected String name;

    protected int weight;

    /**
     * Konstruktor.
     * 
     * @param name
     *            Name
     * @param weight
     *            Gewicht
     */
    public Component(final String name, final int weight)
    {
        this.name = name;
        this.weight = weight;
    }

    /**
     * Liefert den Vorgänger des Objekts.
     * 
     * @return Vorgänger
     */
    public Container getPredecessor()
    {
        return pred;
    }

    /**
     * Setzt den Namen für dieses Objekt.
     * 
     * @param name
     *            neuer Name
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Liefert den Namen dieses Objekts.
     * 
     * @return Name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setzt das Gewicht für dieses Objekt.
     * 
     * @param weight
     *            neues Gewicht
     */
    public void setWeight(final int weight)
    {
        this.weight = weight;
    }

    /**
     * Liefert das Gewicht dieses Objekts.
     * 
     * @return Gewicht
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Methode toString.
     * 
     * @return String-Darstellung
     */
    @Override
    public String toString()
    {
        return name;
    }

    /**
     * Gibt das Objekt aus.
     * 
     * @param indentation
     *            Anzahl der eingerückten Zeichen
     */
    protected void print(final int indentation)
    {
        for (int i = 0; i < indentation; i++)
        {
            System.out.print(" ");
        }
        System.out.println(this);
    }

    /**
     * Gibt das Objekt aus.
     */
    public void print()
    {
        print(0);
    }
}
