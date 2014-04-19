package gui.composite;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Klasse, welche das Datenmodell repräsentiert. Implementiert die Schnittstelle
 * TreeModel, damit direkt als Modell des JTree verwendbar. Basiert auf den
 * Klassen in Datei DesignPatternCompositeExample.java.
 * 
 * @author Rainer Oechsle
 */
public class DPCTreeModel implements TreeModel
{
    private final Container root;

    private final ArrayList<TreeModelListener> treeModelListeners;

    /**
     * Konstruktor.
     * 
     * @param root
     *            Name der Wurzel
     */
    public DPCTreeModel(final Container root)
    {
        this.root = root;
        treeModelListeners = new ArrayList<TreeModelListener>();
    }

    /**
     * Liefert die Wurzel des Baums zurück. Teil der Schnittstelle TreeModel.
     * 
     * @return Wurzel
     */
    @Override
    public Object getRoot()
    {
        return root;
    }

    /**
     * Liefert die Anzahl der Nachfolger eines Knotens zurück. Teil der
     * Schnittstelle TreeModel.
     * 
     * @param parent
     *            interessierender Knoten
     * @return Anzahl der Nachfolger
     */
    @Override
    public int getChildCount(final Object parent)
    {
        final Container group = (Container) parent;
        return group.getNumberOfChildren();
    }

    /**
     * Liefert den Nachfolger mit dem angegebenen Index eines Knotens zurück.
     * Teil der Schnittstelle TreeModel.
     * 
     * @param parent
     *            interessierender Knoten
     * @param index
     *            Index
     * @return betreffender Nachfolger
     */
    @Override
    public Object getChild(final Object parent, final int index)
    {
        final Container group = (Container) parent;
        return group.getChild(index);
    }

    /**
     * Liefert den Index eines Nachfolgers eines Knotens zurück. Teil der
     * Schnittstelle TreeModel.
     * 
     * @param parent
     *            interessierender Knoten
     * @param child
     *            interessierender Nachfolger
     * @return Index des Nachfolgers
     */
    @Override
    public int getIndexOfChild(final Object parent, final Object child)
    {
        final Container group = (Container) parent;
        final Component element = (Component) child;
        return group.getIndexOfChild(element);
    }

    /**
     * Liefert zurück, ob der angegebene Knoten ein Blatt ist oder nicht. Teil
     * der Schnittstelle TreeModel.
     * 
     * @param obj
     *            interessierender Knoten
     * @return true, falls der Knoten ein Blatt ist, ansonsten false
     */
    @Override
    public boolean isLeaf(final Object obj)
    {
        return obj instanceof PrimitiveElement;
    }

    /**
     * Ohne Wirkung. Teil der Schnittstelle TreeModel.
     * 
     * @param path
     *            Pfad
     * @param newValue
     *            neuer Wert
     */
    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue)
    {
    }

    /**
     * Registriert einen TreeModelListener. Teil der Schnittstelle TreeModel.
     * 
     * @param l
     *            zu registrierender Listener
     */
    @Override
    public void addTreeModelListener(final TreeModelListener l)
    {
        treeModelListeners.add(l);
    }

    /**
     * Meldet einen TreeModelListener ab. Teil der Schnittstelle TreeModel.
     * 
     * @param l
     *            abzumeldender Listener
     */
    @Override
    public void removeTreeModelListener(final TreeModelListener l)
    {
        treeModelListeners.remove(l);
    }

    /**
     * Knoten hinzufügen
     * 
     * @param parent
     * @param child
     */
    public void add(final Container parent, final Component child)
    {
        parent.addChild(child);
        fireTreeModelListeners();
    }

    /**
     * Knoten entfernen
     * 
     * @param parent
     * @param child
     */
    public void remove(final Container parent, final Component child)
    {
        parent.removeChild(child);
        fireTreeModelListeners();
    }

    /**
     * Alle Listeners informieren
     * 
     */
    private void fireTreeModelListeners()
    {
        final TreeModelEvent event = new TreeModelEvent(this, new Object[]
        { root });
        for (final TreeModelListener l : treeModelListeners)
        {
            l.treeStructureChanged(event);
        }
    }
}
