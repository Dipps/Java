package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Schnittstelle IntModelListener.
 * 
 * @author Rainer Oechsle
 */
interface IntModelListener
{
    /**
     * Methode zum Benachrichtigen, dass sich das Modell geändert hat.
     */
    public void modelChanged();
}

/**
 * Modellklasse.
 * 
 * @author Rainer Oechsle
 */
class IntModel
{
    public static final int MAXIMUM = 500;
    public static final int MINIMUM =   0;
    /**
     * Einfacher int-Wert.
     */
    private int i;

    /**
     * Beobachter.
     */
    private ArrayList<IntModelListener> listeners;

    /**
     * Konstruktor ohne Argumente.
     */
    public IntModel()
    {
        this(0);
    }

    /**
     * Konstruktor mit Initialwert für int-Attribut.
     */
    public IntModel(int init)
    {
        i = init;
        listeners = new ArrayList<IntModelListener>();
    }

    /**
     * int-Attribut lesen.
     */
    public int getInt()
    {
        return i;
    }

    /**
     * int-Attribut setzen.
     */
    public void setInt(int newValue)
    {
        if(newValue <= MAXIMUM && newValue >= MINIMUM)
        {
            i = newValue;
        }
        fireModelChanged();
    }

    /**
     * Beobachter anmelden.
     */
    public void addIntModelListener(IntModelListener l)
    {
        listeners.add(l);
    }

    /**
     * Beobachter abmelden.
     */
    public void removeIntModelListener(IntModelListener l)
    {
        listeners.remove(l);
    }

    /**
     * Beobachter benachrichtigen.
     */
    private void fireModelChanged()
    {
        for(IntModelListener l : listeners)
        {
            l.modelChanged();
        }
    }
}

/**
 * Darstellung des Modells als Zeichenkette in JLabel.
 * 
 * @author Rainer Oechsle
 */
class NumericIntView extends JLabel implements IntModelListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * Konstruktor.
     */
    public NumericIntView(IntModel im)
    {
        super("" + im.getInt(), SwingConstants.CENTER);
        this.im = im;
        im.addIntModelListener(this);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged()
    {
        setText("" + im.getInt());
    }
}

/**
 * Darstellung des Modells als JProgressBar.
 * 
 * @author Rainer Oechsle
 */
class ProgressIntView extends JPanel implements IntModelListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * JProgressBar.
     */
    private JProgressBar bar;

    /**
     * Konstruktor.
     */
    public ProgressIntView(IntModel im)
    {
        this.im = im;
        im.addIntModelListener(this);
        setLayout(new BorderLayout());
        bar = new JProgressBar(IntModel.MINIMUM, IntModel.MAXIMUM);
        add(bar);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged()
    {
        bar.setValue(im.getInt());
    }
}

/**
 * Darstellung des Modells als Rechteckgrafik.
 * 
 * @author Rainer Oechsle
 */
class GraphicIntView extends JPanel implements IntModelListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * Konstruktor.
     */
    public GraphicIntView(IntModel im)
    {
        this.im = im;
        im.addIntModelListener(this);
    }

    /**
     * Methode paintComponent zum Zeichnen.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.fillRect(10, 10, im.getInt() * (getWidth()-20) / IntModel.MAXIMUM, 20);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged()
    {
        repaint();
    }
}

/**
 * Darstellung des Modells als Eingabefeld.
 * 
 * @author Rainer Oechsle
 */
class TextIntView extends JPanel implements IntModelListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * JTextField.
     */
    private JTextField text;

    /**
     * Konstruktor.
     */
    public TextIntView(IntModel im)
    {
        this.im = im;
        im.addIntModelListener(this);
        setLayout(new BorderLayout());
        text = new JTextField("" + im.getInt());
        TextIntController tic = new TextIntController(im);
        text.addActionListener(tic);
        add(text);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged()
    {
        text.setText("" + im.getInt());
    }
}

/**
 * Darstellung des Modells als JSlider (Schieberegler).
 * 
 * @author Rainer Oechsle
 */
class SliderIntView extends JPanel implements IntModelListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * JSlider.
     */
    private JSlider slider;

    /**
     * Konstruktor.
     */
    public SliderIntView(IntModel im)
    {
        this.im = im;
        im.addIntModelListener(this);
        setLayout(new BorderLayout());
        slider = new JSlider(IntModel.MINIMUM, IntModel.MAXIMUM,
                             im.getInt());
        SliderIntController sic = new SliderIntController(im);
        slider.addChangeListener(sic);
        add(slider);
    }

    /**
     * Methode der Schnittstelle ModelListener.
     */
    public void modelChanged()
    {
        slider.setValue(im.getInt());
    }
}

/**
 * Klasse zum Reagieren auf das Drücken der RETURN-Taste im
 * Eingabefeld.
 * 
 * @author Rainer Oechsle
 */
class TextIntController implements ActionListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * Konstruktor.
     */
    public TextIntController(IntModel im)
    {
        this.im = im;
    }

    /**
     * Methode der Schnittstelle ActionListener.
     */
    public void actionPerformed(ActionEvent evt)
    {
        JTextField tf = (JTextField) evt.getSource();
        try
        {
            int newValue = Integer.parseInt(tf.getText());
            im.setInt(newValue);
        }
        catch(NumberFormatException e)
        {
            tf.setText("" + im.getInt());
        }
    }
}

/**
 * Klasse zum Reagieren auf das Verändern des Schiebereglers.
 * 
 * @author Rainer Oechsle
 */
class SliderIntController implements ChangeListener
{
    /**
     * Modell.
     */
    private IntModel im;

    /**
     * Konstruktor.
     */
    public SliderIntController(IntModel im)
    {
        this.im = im;
    }

    /**
     * Methode der Schnittstelle ChangeListener.
     */
    public void stateChanged(ChangeEvent evt)
    {
        JSlider s = (JSlider) evt.getSource();
        im.setInt(s.getValue());
    }
}

/**
 * Klasse, welche ein Frame darstellt. Enthält main-Methode.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternMVCExample4 extends JFrame
{
    /**
     * Konstruktor.
     */
    public DesignPatternMVCExample4(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IntModel model = new IntModel();
        JPanel panel = new JPanel(new GridLayout(0, 1));

        NumericIntView niView1 = new NumericIntView(model);
        panel.add(niView1);
        ProgressIntView piView1 = new ProgressIntView(model);
        panel.add(piView1);
        GraphicIntView giView1 = new GraphicIntView(model);
        panel.add(giView1);
        TextIntView tiView1 = new TextIntView(model);
        panel.add(tiView1);
        SliderIntView siView1 = new SliderIntView(model);
        panel.add(siView1);

        NumericIntView niView2 = new NumericIntView(model);
        panel.add(niView2);
        ProgressIntView piView2 = new ProgressIntView(model);
        panel.add(piView2);
        GraphicIntView giView2 = new GraphicIntView(model);
        panel.add(giView2);
        TextIntView tiView2 = new TextIntView(model);
        panel.add(tiView2);
        SliderIntView siView2 = new SliderIntView(model);
        panel.add(siView2);

        add(panel);

        setLocation(20, 20);
        setSize(600, 200);
        setVisible(true);
    }

    /**
     * main-Methode.
     */
    public static void main(String[] args)
    {
        new DesignPatternMVCExample4("Einfaches MVC-Beispiel4");
    }
}
