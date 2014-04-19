package gui.lecture;

import java.util.*;

/**
 * Abstrakte Basisklasse aller Elemente. Aus dieser Klasse werden
 * PrimitiveElement und Container abgeleitet.
 * 
 * @author Rainer Oechsle
 */
abstract class Component
{
    /**
     * Referenz auf den Container, in der das jeweilige Element
     * enthalten ist ("Erster" Vorfahre).
     */
    protected Container pred;
    protected String name;
    protected int weight;

    /**
     * Konstruktor.
     * 
     * @param name Name
     * @param weight Gewicht
     */
    public Component(String name, int weight)
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
     * @param name neuer Name
     */
    public void setName(String name)
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
     * @param w neues Gewicht
     */
    public void setWeight(int weight)
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
    public String toString()
    {
        String result = name + " (weight: " + weight + "), ";
        if(pred != null)
        {
            result += "contained in " + pred.getName();
        }
        else
        {
            result += "ROOT";
        }
        return result;
    }

    /**
     * Gibt das Objekt aus.
     * 
     * @param indentation Anzahl der eingerückten Zeichen
     */
    protected void print(int indentation)
    {
        for(int i = 0; i < indentation; i++)
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

/**
 * Klasse zur Repräsentation einer Menge von Elementen (Container).
 * 
 * @author Rainer Oechsle
 */
class Container extends Component
{
    protected ArrayList<Component> children;

    /**
     * Konstruktor.
     * 
     * @param name Name
     */
    public Container(String name, int weight)
    {
        super(name, weight);
        children = new ArrayList<Component>();
    }

    /**
     * Fügt ein neues Element als letztes Element zu der Gruppe hinzu.
     * Setzt ausserdem diese Gruppe als Vorgänger des neuen Elements
     * ein.
     * 
     * @param comp neues Element
     */
    public void addChild(Component comp)
    {
        comp.pred = this;
        children.add(comp);
    }

    /**
     * Entfert ein Element aus der Gruppe.
     * 
     * @param comp zu entfernendes Element
     */
    public void removeChild(Component comp)
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
     * Liefert das Element der Gruppe mit dem angegebenen Index
     * zurück.
     * 
     * @param index Index
     * @return Component
     */
    public Component getChild(int index)
    {
        return children.get(index);
    }

    /**
     * Liefert zu einem gegebenen Element den Index zurück, den dieses
     * Element in der Gruppe besitzt.
     * 
     * @param child Element der Gruppe
     * @return Index
     */
    public int getIndexOfChild(Component child)
    {
        return children.indexOf(child);
    }

    /**
     * Testet, ob das angegebene Element Mitglied der Gruppe ist.
     * 
     * @param comp getestetes Element
     * @return true, falls Mitglied, sonst false
     */
    public boolean hasChild(Component comp)
    {
        return children.contains(comp);
    }

    /**
     * Methode toString.
     * 
     * @return String-Darstellung
     */
    public String toString()
    {
        return "Container: " + super.toString() + " [total weight: "
               + getWeight() + "]";
    }

    /**
     * Gibt das Objekt aus.
     * 
     * @param indentation Anzahl der eingerückten Zeichen
     */
    protected void print(int indentation)
    {
        super.print(indentation);
        for(int i = 0; i < getNumberOfChildren(); i++)
        {
            Component child = getChild(i);
            child.print(indentation + 2);
        }
    }

    /**
     * Liefert das Gewicht dieses Objekts und aller darin enthaltenen
     * Objekte.
     * 
     * @return Gewicht
     */
    public int getWeight()
    {
        int result = weight;
        for(int i = 0; i < getNumberOfChildren(); i++)
        {
            Component child = getChild(i);
            result += child.getWeight();
        }
        return result;
    }
}

/**
 * Klasse für primitive Elemente.
 * 
 * @author Rainer Oechsle
 */
class PrimitiveElement extends Component
{
    private String description;

    /**
     * Konstruktor.
     * 
     * @param name Name
     * @param weight Gewicht
     * @param description Beschreibung
     */
    public PrimitiveElement(String name, int weight, String description)
    {
        super(name, weight);
        this.description = description;
    }

    /**
     * Setzt die Beschreibung für dieses Objekt.
     * 
     * @param description neue Beschreibung
     */
    public void setDescription(String description)
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

    /**
     * Methode toString.
     * 
     * @return String-Darstellung
     */
    public String toString()
    {
        return "Item: " + super.toString() + " [description: " + description
               + "]";
    }
}

/**
 * Klasse zum Testen (enthält nur main-Methode).
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternCompositeExample
{
    /**
     * Methode zum Erzeugen eines Beispielbaums.
     * 
     * @return Wurzel des Beispielbaums
     */
    public static Container makeExampleTree()
    {
        Container root = new Container("big box", 3);
        PrimitiveElement p1 = new PrimitiveElement("paper", 1,
                                                   "table of contents");
        root.addChild(p1);
        Container cont1 = new Container("simple box", 2);
        root.addChild(cont1);
        PrimitiveElement p2 = new PrimitiveElement("CD", 1, "Van Morrison");
        cont1.addChild(p2);
        PrimitiveElement p3 = new PrimitiveElement("heavy stuff", 20,
                                                   "very heavy");
        cont1.addChild(p3);
        Container cont2 = new Container("small box", 1);
        cont1.addChild(cont2);
        PrimitiveElement p4 = new PrimitiveElement("glasses", 1,
                                                   "to see better");
        cont2.addChild(p4);
        Container cont3 = new Container("yet another box", 2);
        cont2.addChild(cont3);
        PrimitiveElement p5 = new PrimitiveElement("newspaper", 5, "TV");
        cont3.addChild(p5);
        PrimitiveElement p6 = new PrimitiveElement("book", 10, "atlas");
        cont2.addChild(p6);

        PrimitiveElement p7 = new PrimitiveElement("pullover", 4,
                                                   "for cold days");
        root.addChild(p7);

        return root;
    }

    /**
     * main-Methode.
     * 
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args)
    {
        Container root = makeExampleTree();
        System.out.println("==============================================");
        root.print();
        System.out.println("==============================================");
        System.out.println("total weight: " + root.getWeight());
    }
}
