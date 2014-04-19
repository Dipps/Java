package gui.composite;

import java.util.ArrayList;

/**
 * Klasse zur Repräsentation einer Menge von Elementen (Container).
 * 
 * toString Methode entfernt
 * 
 * @author Rainer Oechsle
 * 
 */
public class Container extends Component
{
    protected ArrayList<Component> children;

    /**
     * Konstruktor.
     * 
     * @param name
     *            Name
     * @param weight
     *            gewicht
     */
    public Container(final String name, final int weight)
    {
        super(name, weight);
        children = new ArrayList<Component>();
    }

    /**
     * Fügt ein neues Element als letztes Element zu der Gruppe hinzu. Setzt
     * ausserdem diese Gruppe als Vorgänger des neuen Elements ein.
     * 
     * @param comp
     *            neues Element
     */
    public void addChild(final Component comp)
    {
        comp.pred = this;
        children.add(comp);
    }

    /**
     * Entfert ein Element aus der Gruppe.
     * 
     * @param comp
     *            zu entfernendes Element
     */
    public void removeChild(final Component comp)
    {
        comp.pred = null;
        children.remove(comp);
    }

    /**
     * Liefert die Anzahl der Elemente der Gruppe zurück.
     * 
     * @return Anzahl der Elemente
     */
    public int getNumberOfChildren()
    {
        return children.size();
    }

    /**
     * Liefert das Element der Gruppe mit dem angegebenen Index zurück.
     * 
     * @param index
     *            Index
     * @return Component
     */
    public Component getChild(final int index)
    {
        return children.get(index);
    }

    /**
     * Liefert zu einem gegebenen Element den Index zurück, den dieses Element
     * in der Gruppe besitzt.
     * 
     * @param child
     *            Element der Gruppe
     * @return Index
     */
    public int getIndexOfChild(final Component child)
    {
        return children.indexOf(child);
    }

    /**
     * Testet, ob das angegebene Element Mitglied der Gruppe ist.
     * 
     * @param comp
     *            getestetes Element
     * @return true, falls Mitglied, sonst false
     */
    public boolean hasChild(final Component comp)
    {
        return children.contains(comp);
    }

    /**
     * Gibt das Objekt aus.
     * 
     * @param indentation
     *            Anzahl der eingerückten Zeichen
     */
    @Override
    protected void print(final int indentation)
    {
        super.print(indentation);
        for (int i = 0; i < getNumberOfChildren(); i++)
        {
            final Component child = getChild(i);
            child.print(indentation + 2);
        }
    }

    /**
     * Liefert das Gewicht dieses Objekts und aller darin enthaltenen Objekte.
     * 
     * @return Gewicht
     */
    @Override
    public int getWeight()
    {
        int result = weight;
        for (int i = 0; i < getNumberOfChildren(); i++)
        {
            final Component child = getChild(i);
            result += child.getWeight();
        }
        return result;
    }
}
