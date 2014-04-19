package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Schnittstelle BooleanModelListener.
 * 
 * @author Rainer Oechsle
 */
interface BooleanModelListener1
{
    /**
     * Methode zum Benachrichtigen, dass sich das Modell geändert hat.
     * Der neue Wert des Modells wird als Parameter übergeben.
     * Alternative: public void modelChanged(BooleanModel source); In
     * diesem Fall wird eine Referenz auf das Modell bei der
     * Benachrichtigung übergeben (s. Vorbild Observer, auch
     * ActionListener mit ActionEvent). Vom Modell können dann die
     * benötigten neuen Werte abgefragt werden. Bei komplexeren
     * Datenstrukturen besser geeignet.
     */
    public void modelChanged(boolean newValue);
}

/**
 * Modellklasse.
 * 
 * @author Rainer Oechsle
 */
class BooleanModel1
{
    /**
     * Einfacher boolean-Wert.
     */
    private boolean b;

    /**
     * Beobachter.
     */
    private ArrayList<BooleanModelListener1> listeners;

    /**
     * Konstruktor ohne Argumente.
     */
    public BooleanModel1()
    {
        this(false);
    }

    /**
     * Konstruktor mit Initialwert für int-Attribut.
     */
    public BooleanModel1(boolean init)
    {
        b = init;
        listeners = new ArrayList<BooleanModelListener1>();
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
    public void addBooleanModelListener(BooleanModelListener1 l)
    {
        listeners.add(l);
    }

    /**
     * Beobachter abmelden.
     */
    public void removeBooleanModelListener(BooleanModelListener1 l)
    {
        listeners.remove(l);
    }

    /**
     * Beobachter benachrichtigen.
     */
    private void fireModelChanged()
    {
        for(BooleanModelListener1 l : listeners)
        {
            l.modelChanged(b);
        }
        /*
         * Iterator<BooleanModelListener1> iter =
         * listeners.iterator(); while(iter.hasNext()) {
         * BooleanModelListener1 l = iter.next(); l.modelChanged(b); }
         */
        /*
         * for(int i = 0; i < listeners.size(); i++) {
         * BooleanModelListener1 l = listeners.get(i);
         * l.modelChanged(b); }
         */
    }
}

/**
 * Darstellung des Modells als Zeichenkette (true / false) in JLabel.
 * 
 * @author Rainer Oechsle
 */
class TextView1 extends JLabel implements BooleanModelListener1
{
    /**
     * Konstruktor.
     */
    public TextView1(boolean initValue)
    {
        super("" + initValue);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged(boolean newValue)
    {
        setText("" + newValue);
    }
}

/**
 * Darstellung des Modells als JToggleButton (kann auch Checkbox oder
 * Radiobutton sein).
 * 
 * @author Rainer Oechsle
 */
class TogglebuttonView1 extends JPanel implements BooleanModelListener1
{
    /**
     * JToggleButton.
     */
    private JToggleButton tb;

    /**
     * Konstruktor.
     */
    public TogglebuttonView1(JToggleButton tb)
    {
        this.tb = tb;
        setLayout(new BorderLayout());
        add(tb);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged(boolean newValue)
    {
        tb.setSelected(newValue);
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
class BooleanController1 implements ActionListener
{
    /**
     * Modell.
     */
    private BooleanModel1 bm;

    /**
     * Konstruktor.
     */
    public BooleanController1(BooleanModel1 bm)
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
public class DesignPatternMVCExample1 extends JFrame
{
    /**
     * Konstruktor.
     */
    public DesignPatternMVCExample1(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        BooleanModel1 model = new BooleanModel1();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        BooleanController1 control = new BooleanController1(model);

        for(int i = 0; i < 3; i++)
        {
            TextView1 tView = new TextView1(model.getBoolean());
            model.addBooleanModelListener(tView);
            panel.add(tView);

            JCheckBox cb = new JCheckBox("Darstellung als Checkbox",
                                         model.getBoolean());
            TogglebuttonView1 cbView = createView(cb, model, control);
            panel.add(cbView);

            JRadioButton rb = new JRadioButton("Darstellung als Radiobutton",
                                               model.getBoolean());
            TogglebuttonView1 rbView = createView(rb, model, control);
            panel.add(rbView);

            JToggleButton tb = new JToggleButton("Darstellung als Togglebutton",
                                                 model.getBoolean());
            TogglebuttonView1 tbView = createView(tb, model, control);
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
    private TogglebuttonView1 createView(JToggleButton tb, BooleanModel1 bm,
                                         BooleanController1 bc)
    {
        tb.addActionListener(bc);
        TogglebuttonView1 tbView = new TogglebuttonView1(tb);
        bm.addBooleanModelListener(tbView);
        return tbView;
    }

    /**
     * main-Methode.
     */
    public static void main(String[] args)
    {
        new DesignPatternMVCExample1("Einfaches MVC-Beispiel1");
    }
}
