package gui.lecture;

//import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

/**
 * Klasse, welche das Datenmodell repräsentiert. Implementiert die
 * Schnittstelle TreeModel, damit direkt als Modell des JTree
 * verwendbar. Basiert auf den Klassen in Datei
 * DesignPatternCompositeExample.java.
 * 
 * @author Rainer Oechsle
 */
class DPCTreeModel implements TreeModel
{
    private Container root;
    //private ArrayList<TreeModelListener> treeModelListeners;

    /**
     * Konstruktor.
     * 
     * @param rootName Name der Wurzel
     */
    public DPCTreeModel(Container root)
    {
        this.root = root;
        //treeModelListeners = new ArrayList<TreeModelListener>();
    }

    /**
     * Liefert die Wurzel des Baums zurück. Teil der Schnittstelle
     * TreeModel.
     * 
     * @return Wurzel
     */
    public Object getRoot()
    {
        return root;
    }

    /**
     * Liefert die Anzahl der Nachfolger eines Knotens zurück. Teil
     * der Schnittstelle TreeModel.
     * 
     * @param parent interessierender Knoten
     * @return Anzahl der Nachfolger
     */
    public int getChildCount(Object parent)
    {
        Container group = (Container) parent;
        return group.getNumberOfChildren();
    }

    /**
     * Liefert den Nachfolger mit dem angegebenen Index eines Knotens
     * zurück. Teil der Schnittstelle TreeModel.
     * 
     * @param parent interessierender Knoten
     * @param index Index
     * @return betreffender Nachfolger
     */
    public Object getChild(Object parent, int index)
    {
        Container group = (Container) parent;
        return group.getChild(index);
    }

    /**
     * Liefert den Index eines Nachfolgers eines Knotens zurück. Teil
     * der Schnittstelle TreeModel.
     * 
     * @param parent interessierender Knoten
     * @param child interessierender Nachfolger
     * @return Index des Nachfolgers
     */
    public int getIndexOfChild(Object parent, Object child)
    {
        Container group = (Container) parent;
        Component element = (Component) child;
        return group.getIndexOfChild(element);
    }

    /**
     * Liefert zurück, ob der angegebene Knoten ein Blatt ist oder
     * nicht. Teil der Schnittstelle TreeModel.
     * 
     * @param obj interessierender Knoten
     * @return true, falls der Knoten ein Blatt ist, ansonsten false
     */
    public boolean isLeaf(Object obj)
    {
        return obj instanceof PrimitiveElement;
    }

    /**
     * Ohne Wirkung. Teil der Schnittstelle TreeModel.
     * 
     * @param path Pfad
     * @param newValue neuer Wert
     */
    public void valueForPathChanged(TreePath path, Object newValue)
    {
    }

    /**
     * Registriert einen TreeModelListener. Teil der Schnittstelle
     * TreeModel.
     * 
     * @param l zu registrierender Listener
     */
    public void addTreeModelListener(TreeModelListener l)
    {
        //treeModelListeners.add(l);
    }

    /**
     * Meldet einen TreeModelListener ab. Teil der Schnittstelle
     * TreeModel.
     * 
     * @param l abzumeldender Listener
     */
    public void removeTreeModelListener(TreeModelListener l)
    {
        //treeModelListeners.remove(l);
    }
}

/**
 * Klasse zum Testen des eigenen Baummodells.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternCompositeExampleAsTreeModel extends JFrame
{
    /**
     * Konstruktor.
     * 
     * @param title Titel
     */
    public DesignPatternCompositeExampleAsTreeModel(String title)
    {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Container root = DesignPatternCompositeExample.makeExampleTree();
        DPCTreeModel model = new DPCTreeModel(root);
        JTree tree = new JTree(model);
        JScrollPane pane = new JScrollPane(tree);

        add(pane);
        setLocation(500, 100);
        setSize(700, 250);
        setVisible(true);
    }

    /**
     * main-Methode.
     * 
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception exc)
        {
        }

        new DesignPatternCompositeExampleAsTreeModel("My Own Tree Model "
                                                     + "Using The Composite "
                                                     + "Design Pattern");
    }
}
