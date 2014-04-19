package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Schnittstelle BooleanModelListener. GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
interface BooleanModelListener3
{
    /**
     * Methode zum Benachrichtigen, dass sich das Modell geändert hat.
     * GEÄNDERT!!!! Die Methode besitzt keinen Parameter mehr.
     */
    public void modelChanged();
}

/**
 * Modellklasse. GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class BooleanModel3
{
    /**
     * Einfacher boolean-Wert.
     */
    private boolean b;

    /**
     * Beobachter.
     */
    private ArrayList<BooleanModelListener3> listeners;

    /**
     * Konstruktor ohne Argumente.
     */
    public BooleanModel3()
    {
        this(false);
    }

    /**
     * Konstruktor mit Initialwert für int-Attribut.
     */
    public BooleanModel3(boolean init)
    {
        b = init;
        listeners = new ArrayList<BooleanModelListener3>();
    }

    /**
     * boolean-Attribut lesen.
     */
    public boolean getBoolean()
    {
        return b;
    }

    /**
     * boolean-Attribut setzen.
     */
    public void setBoolean(boolean newValue)
    {
        b = newValue;
        fireModelChanged();
    }

    /**
     * Beobachter anmelden.
     */
    public void addBooleanModelListener(BooleanModelListener3 l)
    {
        listeners.add(l);
    }

    /**
     * Beobachter abmelden.
     */
    public void removeBooleanModelListener(BooleanModelListener3 l)
    {
        listeners.remove(l);
    }

    /**
     * Beobachter benachrichtigen. GEÄNDERT!!!! Die Methode
     * modelChanged wird ohne Parameter aufgerufen.
     */
    private void fireModelChanged()
    {
        for(BooleanModelListener3 l : listeners)
        {
            l.modelChanged();
        }
    }
}

/**
 * Darstellung des Modells als Zeichenkette (true / false) in JLabel.
 * GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class TextView3 extends JLabel implements BooleanModelListener3
{
    /**
     * Modell. GEÄNDERT!!! Das Modell-Attribut gab es vorher nicht.
     */
    private BooleanModel3 bm;

    /**
     * Konstruktor. GEÄNDERT!!! Der Modell-Parameter wird im
     * entsprechenden Attribut gemerkt.
     */
    public TextView3(BooleanModel3 bm)
    {
        super("" + bm.getBoolean());
        this.bm = bm;
    }

    /**
     * Methode der Schnittstelle ModelListener. GEÄNDERT!!! Die
     * Methode hat keinen Parameter mehr. Statt dessen wird der
     * aktuelle Modellwert über die Modellreferenz beschafft.
     */
    public void modelChanged()
    {
        setText("" + bm.getBoolean());
    }
}

/**
 * Darstellung des Modells als JToggleButton (kann auch Checkbox oder
 * Radiobutton sein). GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class TogglebuttonView3 extends JPanel implements BooleanModelListener3
{
    /**
     * Modell. GEÄNDERT!!! Das Modell-Attribut gab es vorher nicht.
     */
    private BooleanModel3 bm;

    /**
     * JToggleButton.
     */
    private JToggleButton tb;

    /**
     * Konstruktor. GEÄNDERT!!! Der Modell-Parameter wird im
     * entsprechenden Attribut gemerkt.
     */
    public TogglebuttonView3(BooleanModel3 bm, JToggleButton tb)
    {
        this.bm = bm;
        this.tb = tb;
        setLayout(new BorderLayout());
        add(tb);
    }

    /**
     * Methode der Schnittstelle ModelListener. GEÄNDERT!!! Die
     * Methode hat keinen Parameter mehr. Statt dessen wird der
     * aktuelle Modellwert über die Modellreferenz beschafft.
     */
    public void modelChanged()
    {
        tb.setSelected(bm.getBoolean());
    }
}

/**
 * Klasse zum Reagieren auf das Selektieren / Deselektieren einer
 * Checkbox, eines Radiobuttons oder eines Togglebuttons. Dies ist
 * möglich, da JRadioButton und JCheckBox aus JToggleButton abgeleitet
 * sind.
 * 
 * @author Rainer Oechsle
 */
class BooleanController3 implements ActionListener
{
    /**
     * Modell.
     */
    private BooleanModel3 bm;

    /**
     * Konstruktor.
     */
    public BooleanController3(BooleanModel3 bm)
    {
        this.bm = bm;
    }

    /**
     * Methode der Schnittstelle ActionListener.
     */
    public void actionPerformed(ActionEvent evt)
    {
        JToggleButton tb = (JToggleButton) evt.getSource();
        bm.setBoolean(tb.isSelected());
    }
}

/**
 * Klasse, welche ein Frame darstellt. Enthält main-Methode.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternMVCExample3 extends JFrame
{
    /**
     * Konstruktor.
     */
    public DesignPatternMVCExample3(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        BooleanModel3 model = new BooleanModel3();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        BooleanController3 control = new BooleanController3(model);

        for(int i = 0; i < 3; i++)
        {
            TextView3 tView = new TextView3(model);
            model.addBooleanModelListener(tView);
            panel.add(tView);

            JCheckBox cb = new JCheckBox("Darstellung als Checkbox",
                                         model.getBoolean());
            TogglebuttonView3 cbView = createView(cb, model, control);
            panel.add(cbView);

            JRadioButton rb = new JRadioButton("Darstellung als Radiobutton",
                                               model.getBoolean());
            TogglebuttonView3 rbView = createView(rb, model, control);
            panel.add(rbView);

            JToggleButton tb = new JToggleButton(
                                                 "Darstellung als Togglebutton",
                                                 model.getBoolean());
            TogglebuttonView3 tbView = createView(tb, model, control);
            panel.add(tbView);
        }

        setContentPane(panel);

        setLocation(20, 20);
        setSize(350, 350);
        setVisible(true);
    }

    /**
     * Private Methode zum Erzeugen einer View. Meldet Controller an
     * Togglebutton als Listener an und meldet neu erzeugte
     * TogglebuttonView am Model als Listener an.     * ****************************************************************************
     */
    private TogglebuttonView3 createView(JToggleButton tb, BooleanModel3 bm,
                                         BooleanController3 bc)
    {
        tb.addActionListener(bc);
        TogglebuttonView3 tbView = new TogglebuttonView3(bm, tb);
        bm.addBooleanModelListener(tbView);
        return tbView;
    }

    /**
     * main-Methode.
     */
    public static void main(String[] args)
    {
        new DesignPatternMVCExample3("Einfaches MVC-Beispiel3");
    }
}
