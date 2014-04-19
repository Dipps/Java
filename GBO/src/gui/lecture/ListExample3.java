package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Implementiert die Schnittstelle ListModel, damit direkt als Modell
 * von JList verwendbar.
 * 
 * @author Rainer Oechsle
 */
class MyListModel implements ListModel<Integer>
{
    private int size;
    private ArrayList<ListDataListener> listeners;

    /**
     * Konstruktor.
     */
    public MyListModel()
    {
        size = 0;
        listeners = new ArrayList<ListDataListener>();
    }

    /**
     * Gibt das n-te Element zurück. Teil der Schnittstelle ListModel.
     */
    public Integer getElementAt(int index)
    {
        if(index < 0 || index >= size)
        {
            return null;
        }
        return new Integer(index + 1);
    }

    /**
     * Gibt die Anzahl der Elemente zurück. Teil der Schnittstelle ListModel.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Registriert einen ListDataListener. Teil der Schnittstelle ListModel.
     * 
     * @param l zu registrierender Listener
     */
    public void addListDataListener(ListDataListener l)
    {
        //System.out.println("addListDataListener(" + l + ")");
        listeners.add(l);
    }

    /**
     * Meldet einen ListDataListener ab. Teil der Schnittstelle ListModel.
     * 
     * @param l abzumeldender Listener
     */
    public void removeListDataListener(ListDataListener l)
    {
        listeners.remove(l);
    }

    /**
     * F&uuml;gt das n&auml;chste Element hinzu. 
     * NICHT Teil der Schnittstelle ListModel.
     * 
     * @param elem hinzuzuf&uuml;gendes Element
     */
    public void add()
    {
        size++;

        ListDataEvent event = new ListDataEvent(this,
                                                ListDataEvent.INTERVAL_ADDED,
                                                size - 1, size - 1);
        for(ListDataListener l : listeners)
        {
            l.intervalAdded(event);
        }
    }
}

/**
 * Testen von MyListModel.
 * 
 * @author Rainer Oechsle
 */
public class ListExample3 extends JFrame implements ActionListener
{
    private MyListModel listModel;

    /**
     * Konstruktor.
     */
    public ListExample3(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new MyListModel();
        JList<Integer> list = new JList<Integer>(listModel);
        JScrollPane pane = new JScrollPane(list);
        add(pane, BorderLayout.CENTER);
        JButton b = new JButton("Element einfügen");
        add(b, BorderLayout.SOUTH);
        b.addActionListener(this);

        setSize(400, 200);
        setVisible(true);
    }

    /**
     * ausgeführt, falls man den Button gedr&uuml;ckt hat.
     */
    public void actionPerformed(ActionEvent evt)
    {
        listModel.add();
    }

    /**
     * main.
     */
    public static void main(String[] args)
    {
        new ListExample3("Beispiel für Listenmodell");
    }
}
