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
interface BooleanModelListener2
{
    /**
     * Methode zum Benachrichtigen, dass sich das Modell geändert hat.
     * GEÄNDERT!!!! Die Methode hat jetzt einen Parameter für die
     * Quelle.
     */
    public void modelChanged(BooleanModel2 source);
}

/**
 * Modellklasse. GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class BooleanModel2
{
    /**
     * Einfacher boolean-Wert.
     */
    private boolean b;

    /**
     * Beobachter.
     */
    private ArrayList<BooleanModelListener2> listeners;

    /**
     * Konstruktor ohne Argumente.
     */
    public BooleanModel2()
    {
        this(false);
    }

    /**
     * Konstruktor mit Initialwert für int-Attribut.
     */
    public BooleanModel2(boolean init)
    {
        b = init;
        listeners = new ArrayList<BooleanModelListener2>();
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
    public void addBooleanModelListener(BooleanModelListener2 l)
    {
        listeners.add(l);
    }

    /**
     * Beobachter abmelden.
     */
    public void removeBooleanModelListener(BooleanModelListener2 l)
    {
        listeners.remove(l);
    }

    /**
     * Beobachter benachrichtigen. GEÄNDERT!!!! Das Modell übergibt
     * sich selbst als Parameter beim Aufruf der Methode modelChanged.
     */
    private void fireModelChanged()
    {
        for(BooleanModelListener2 l : listeners)
        {
            l.modelChanged(this);
        }
    }
}

/**
 * Darstellung des Modells als Zeichenkette (true / false) in JLabel.
 * GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class TextView2 extends JLabel implements BooleanModelListener2
{
    /**
     * Konstruktor.
     */
    public TextView2(boolean initValue)
    {
        super("" + initValue);
    }

    /**
     * Methode der Schnittstelle ModelListener. GEÄNDERT!!! Die
     * Methode hat jetzt als Parameter eine Modellreferenz, über die
     * der aktuelle Wert beschafft wird.
     */
    public void modelChanged(BooleanModel2 source)
    {
        setText("" + source.getBoolean());
    }
}

/**
 * Darstellung des Modells als JToggleButton (kann auch Checkbox oder
 * Radiobutton sein). GEÄNDERT!!!!
 * 
 * @author Rainer Oechsle
 */
class TogglebuttonView2 extends JPanel implements BooleanModelListener2
{
    /**
     * JToggleButton.
     */
    private JToggleButton tb;

    /**
     * Konstruktor.
     */
    public TogglebuttonView2(JToggleButton tb)
    {
        this.tb = tb;
        setLayout(new BorderLayout());
        add(tb);
    }

    /**
     * Methode der Schnittstelle ModelListener. GEÄNDERT!!! Die
     * Methode hat jetzt als Parameter eine Modellreferenz, über die
     * der aktuelle Wert beschafft wird.
     */
    public void modelChanged(BooleanModel2 source)
    {
        tb.setSelected(source.getBoolean());
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
class BooleanController2 implements ActionListener
{
    /**
     * Modell.
     */
    private BooleanModel2 bm;

    /**
     * Konstruktor.
     */
    public BooleanController2(BooleanModel2 bm)
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
public class DesignPatternMVCExample2 extends JFrame
{
    /**
     * Konstruktor.
     */
    public DesignPatternMVCExample2(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        BooleanModel2 model = new BooleanModel2();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        BooleanController2 control = new BooleanController2(model);

        for(int i = 0; i < 3; i++)
        {
            TextView2 tView = new TextView2(model.getBoolean());
            model.addBooleanModelListener(tView);
            panel.add(tView);

            JCheckBox cb = new JCheckBox("Darstellung als Checkbox",
                                         model.getBoolean());
            TogglebuttonView2 cbView = createView(cb, model, control);
            panel.add(cbView);

            JRadioButton rb = new JRadioButton("Darstellung als Radiobutton",
                                               model.getBoolean());
            TogglebuttonView2 rbView = createView(rb, model, control);
            panel.add(rbView);

            JToggleButton tb = new JToggleButton(
                                                 "Darstellung als Togglebutton",
                                                 model.getBoolean());
            TogglebuttonView2 tbView = createView(tb, model, control);
            panel.add(tbView);
        }

        add(panel);

        setLocation(20, 20);
        setSize(350, 350);
        setVisible(true);
    }

    /**
     * Private Methode zum Erzeugen einer View. Meldet Controller an
     * Togglebutton als Listener an und meldet neu erzeugte
     * TogglebuttonView am Model als Listener an.
     */
    private TogglebuttonView2 createView(JToggleButton tb, BooleanModel2 bm,
                                         BooleanController2 bc)
    {
        tb.addActionListener(bc);
        TogglebuttonView2 tbView = new TogglebuttonView2(tb);
        bm.addBooleanModelListener(tbView);
        return tbView;
    }

    /**
     * main-Methode.
     */
    public static void main(String[] args)
    {
        new DesignPatternMVCExample2("Einfaches MVC-Beispiel2");
    }
}
