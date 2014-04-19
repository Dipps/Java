package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Schnittstelle, die ein Objekt implementieren muss, um sich an einem
 * Objekt des Typs PolynomModel als Listener anmelden zu k&ouml;nnen.
 * 
 * @author Rainer Oechsle
 */
interface PolynomListener
{
    /**
     * Methode zum Benachrichtigen eines angemeldeten Objekts.
     */
    public void polynomChanged();
}

/**
 * Modellklasse für die zentrale Verwaltung der Polynomdaten. Bietet
 * die M&ouml;glichkeit, PolynomListener an dem Modell anzumelden, die
 * bei &Auml;nderung des Modells benachrichtigt werden.
 * 
 * @author Rainer Oechsle
 */
class PolynomModel
{
    /**
     * Koeffizienten. Es gibt einen Koeffizienten mehr als der Grad
     * des Polynoms. Beispiel: y = a * x + b, Grad 1, 2 Koeffizienten
     * a und b.
     */
    private double[] coefficients;

    /**
     * Koeffizienten der Ableitung.
     */
    private double[] coefficientsDerived;

    /**
     * Speicher von PolynomListener-Objekten.
     */
    private ArrayList<PolynomListener> listeners;

    /**
     * Konstruktor des Modells.
     * 
     * @param degree Grad des Polynoms
     */
    public PolynomModel(int degree)
    {
        if(degree < 0)
        {
            degree = 0;
        }
        coefficients = new double[degree + 1];
        coefficientsDerived = new double[degree];
        listeners = new ArrayList<PolynomListener>();
    }

    /**
     * Methode zum Anmelden eines PolynomListener-Objekts.
     * 
     * @param l PolynomListener-Objekt
     */
    public void addPolynomListener(PolynomListener l)
    {
        listeners.add(l);
    }

    /**
     * Methode zum Abmelden eines PolynomListener-Objekts.
     * 
     * @param l PolynomListener-Objekt
     */
    public void removePolynomListener(PolynomListener l)
    {
        listeners.remove(l);
    }

    /**
     * Private Methode zum Benachrichtigen aller angemeldeten
     * PolynomListener-Objekte.
     */
    private void firePolynomChanged()
    {
        for(PolynomListener l : listeners)
        {
            l.polynomChanged();
        }
    }

    /**
     * Liefert den Grad des Polynoms zur&uuml;ck. Der Grad ist um eins
     * niedriger als die Anzahl der Koeffizienten.
     */
    public int getDegree()
    {
        return coefficients.length - 1;
    }

    /**
     * Einen der Koeffizienten ändern.
     */
    public void setCoefficient(int index, double newValue)
    {
        coefficients[index] = newValue;
        if(index > 0)
        {
            coefficientsDerived[index - 1] = index * coefficients[index];
        }
        firePolynomChanged();
    }

    /**
     * Einen Koeffizienten lesen.
     */
    public double getCoefficient(int index)
    {
        return coefficients[index];
    }

    /**
     * Private, statische Methode zum Berechnen des Funktionswerts des
     * Polynoms, dessen Koeffizienten als Parameter angegeben werden,
     * an der Stelle x mit Hilfe des Horner-Schemas.
     */
    private static double horner(double x, double[] coeff)
    {
        if(coeff.length == 0)
        {
            return 0;
        }
        double result = coeff[coeff.length - 1];
        for(int i = coeff.length - 2; i >= 0; i--)
        {
            result = result * x + coeff[i];
        }
        return result;
    }

    /**
     * Methode zum Berechnen des Funktionswerts dieses Polynoms an der
     * Stelle x mit Hilfe des Horner-Schemas.
     */
    public double computeFunctionValue(double x)
    {
        return horner(x, coefficients);
    }

    /**
     * Methode zum Berechnen mehrerer Funktionswerte dieses Polynoms
     * an den Stellen x. Rückgabe der berechenten Werte als Feld.
     */
    public double[] computeFunctionValues(double[] x)
    {
        double[] y = new double[x.length];
        int numberOfValues = x.length;
        if(x.length > y.length)
        {
            numberOfValues = y.length;
        }
        for(int i = 0; i < numberOfValues; i++)
        {
            y[i] = computeFunctionValue(x[i]);
        }
        return y;
    }

    /**
     * Methode zum Berechnen der Steigung dieses Polynoms an der
     * Stelle x mit Hilfe des Horner-Schemas.
     */
    public double computeSlope(double x)
    {
        return horner(x, coefficientsDerived);
    }
}

/**
 * Klasse FormulaView zur Visualisierung der Daten eines
 * Modells-Objekts als Funktionsterm.
 * 
 * @author Rainer Oechsle
 */
class FormulaView extends JLabel implements PolynomListener
{
    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Konstruktor.
     * 
     * @param pm Modell, dessen Daten dargestellt werden
     */
    public FormulaView(PolynomModel pm)
    {
        super("", SwingConstants.RIGHT);
        this.pm = pm;
        pm.addPolynomListener(this);
        setFont(new Font("TimesRoman", Font.PLAIN, 24));
        polynomChanged();
    }

    /**
     * Methode, die bei einer &Auml;nderung des Modells aufgerufen
     * wird.
     */
    public void polynomChanged()
    {
        boolean hasWritten = false;
        String s = "f(x) =";
        for(int i = pm.getDegree(); i >= 0; i--)
        {
            double c = pm.getCoefficient(i);
            if(c != 0)
            {
                s += " ";
                if(c < 0)
                {
                    s += "- ";
                }
                else if(hasWritten)
                {
                    s += "+ ";
                }
                s += Math.abs(c);
                if(i == 1)
                {
                    s += " * x";
                }
                else if(i > 1)
                {
                    s += " * x**" + i;
                }
                hasWritten = true;
            }
        }
        if(!hasWritten)
        {
            s += " 0";
        }
        setText(s);
    }
}

/**
 * Klasse SliderView zur Visualisierung der Daten eines
 * Modells-Objekts durch Slider (ein Slider pro Koeffizient).
 * 
 * @author Rainer Oechsle
 */
class SliderView extends JPanel implements PolynomListener
{
    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Die Slider.
     */
    private JSlider[] sliders;

    /**
     * Pro Slider alle Listener.
     */
    private ChangeListener[][] listeners;

    /**
     * Konstruktor.
     * 
     * @param pm Modell, dessen Daten dargestellt werden
     */
    public SliderView(PolynomModel pm)
    {
        this.pm = pm;
        pm.addPolynomListener(this);
        setLayout(new GridLayout(0, 1));
        sliders = new JSlider[pm.getDegree() + 1];
        listeners = new ChangeListener[sliders.length][];
        for(int i = pm.getDegree(); i >= 0; i--)
        {
            JPanel p = new JPanel(new FlowLayout());
            p.add(new JLabel("a" + i + ": "));
            sliders[i] = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 0);
            sliders[i].setPaintTicks(true);
            sliders[i].setPaintLabels(true);
            sliders[i].setMajorTickSpacing(5);
            sliders[i].setMinorTickSpacing(1);
            SliderController sc = new SliderController(pm, i);
            sliders[i].addChangeListener(sc);
            p.add(sliders[i]);
            add(p);
        }
        polynomChanged();
    }

    /**
     * Methode, die bei einer &Auml;nderung des Modells aufgerufen
     * wird.
     */
    public void polynomChanged()
    {
        disableChangeListeners();
        for(int i = pm.getDegree(); i >= 0; i--)
        {
            int c = (int) pm.getCoefficient(i);
            sliders[i].setValue(c);
        }
        enableChangeListeners();
    }

    /**
     * Methode, die alle ChangeListeners speichert und diese dann
     * abmeldet.
     */
    private void disableChangeListeners()
    {
        for(int i = 0; i < sliders.length; i++)
        {
            listeners[i] = sliders[i].getChangeListeners();
            for(int j = 0; j < listeners[i].length; j++)
            {
                sliders[i].removeChangeListener(listeners[i][j]);
            }
        }
    }

    /**
     * Methode, die alle gespeicherten ChangeListeners wieder
     * anmeldet.
     */
    private void enableChangeListeners()
    {
        for(int i = 0; i < sliders.length; i++)
        {
            for(int j = 0; j < listeners[i].length; j++)
            {
                sliders[i].addChangeListener(listeners[i][j]);
            }
        }
    }
}

/**
 * Klasse TextView zur Visualisierung der Daten eines Modells-Objekts
 * durch Textfelder (ein Textfeld pro Koeffizient).
 * 
 * @author Rainer Oechsle
 */
class CoefficientsView extends JPanel implements PolynomListener
{
    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Die Textfelder.
     */
    private JTextField[] fields;

    /**
     * Konstruktor.
     * 
     * @param pm Modell, dessen Daten dargestellt werden
     */
    public CoefficientsView(PolynomModel pm)
    {
        this.pm = pm;
        pm.addPolynomListener(this);
        setLayout(new GridLayout(1, 0));
        fields = new JTextField[pm.getDegree() + 1];
        for(int i = pm.getDegree(); i >= 0; i--)
        {
            JPanel p = new JPanel(new BorderLayout());
            p.add(new JLabel("a" + i + ": "), BorderLayout.WEST);
            fields[i] = new JTextField("0.0");
            TextFieldController tfc = new TextFieldController(pm, i);
            fields[i].addActionListener(tfc);
            p.add(fields[i], BorderLayout.CENTER);
            add(p);
        }
        polynomChanged();
    }

    /**
     * Methode, die bei einer &Auml;nderung des Modells aufgerufen
     * wird.
     */
    public void polynomChanged()
    {
        for(int i = pm.getDegree(); i >= 0; i--)
        {
            double c = pm.getCoefficient(i);
            fields[i].setText("" + c);
        }
    }
}

/**
 * Klasse GraphicView zur Visualisierung der Daten eines
 * Modells-Objekts als Kurve.
 * 
 * @author Rainer Oechsle
 */
class CurveView extends JPanel implements PolynomListener
{
    /**
     * Skalierung.
     */
    private int scale = 20;

    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Tangente im Moment sichtbar oder nicht.
     */
    private boolean tangVisible = false;

    /**
     * x-Koordinate, für die Tangente gezeichnet wird.
     */
    private int xTang;

    /**
     * Konstruktor.
     * 
     * @param pm Modell, dessen Daten dargestellt werden
     */
    public CurveView(PolynomModel pm)
    {
        this.pm = pm;
        pm.addPolynomListener(this);
        MouseController mc = new MouseController(this);
        addMouseListener(mc);
        MouseMotionController mmc = new MouseMotionController(this);
        addMouseMotionListener(mmc);
    }

    /**
     * Methode zum Lesn der Skalierung.
     */
    public int getScale()
    {
        return scale;
    }

    /**
     * Methode zum Ändern der Skalierung.
     */
    public void setScale(int s)
    {
        scale = s;
        repaint();
    }

    /**
     * Methode zum Ändern der x-Koordinate, für die die Tangente
     * gezeichnet wird.
     */
    public void setXTang(int x)
    {
        xTang = x;
        repaint();
    }

    /**
     * Methode zum Setzen der Sichtbarkeit der Tangente.
     */
    public void setTangVisible(boolean b)
    {
        tangVisible = b;
        repaint();
    }

    /**
     * Methode zum Zeichnen der Grafik.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        drawAxes(g, width, height);

        double[] x = new double[width];
        for(int i = 0; i < width; i++)
        {
            x[i] = (double) (i - width / 2) / (double) scale;
        }

        double[] y = pm.computeFunctionValues(x);

        int[] pixelX = new int[width];
        int[] pixelY = new int[width];
        for(int i = 0; i < width; i++)
        {
            pixelX[i] = i;
            // pixelX[i] = (int) (x[i] * scale) + width / 2;
            pixelY[i] = height / 2 - (int) (y[i] * scale);
        }
        g.drawPolyline(pixelX, pixelY, width);

        if(tangVisible)
        {
            drawTang(g, width, height);
        }
    }

    /**
     * Private Methode zum Zeichnen der Koordinatenachsen. Wird aus
     * paintComponent aufgerufen.
     */
    private void drawAxes(Graphics g, int width, int height)
    {
        // x:
        g.drawLine(0, height / 2, width, height / 2);
        // y:
        g.drawLine(width / 2, 0, width / 2, height);
        // ticks for x:
        int numberOfX = width / scale;
        for(int i = -numberOfX/2; i <= numberOfX/2; i++)
        {
            g.drawLine(width / 2 + i * scale, height / 2 - 4,
                       width / 2 + i * scale, height / 2 + 4);
        }
        // ticks for y:
        int numberOfY = height / scale;
        for(int i = -numberOfY/2; i <= numberOfY/2; i++)
        {
            g.drawLine(width / 2 - 4, height / 2 + i * scale, 
                       width / 2 + 4, height / 2 + i * scale);
        }
    }

    /**
     * Private Methode zum Zeichnen der Tangente. Wird aus
     * paintComponent aufgerufen.
     */
    private void drawTang(Graphics g, int width, int height)
    {
        double realX = ((double) xTang - (double) width / (double) 2)
                       / (double) scale;
        double realY = pm.computeFunctionValue(realX);
        if((realY * scale < height / 2) && realY * scale > -height / 2)
        {
            g.setColor(Color.RED);
            int yTang = height / 2 - (int) (realY * scale);
            g.fillOval(xTang - 5, yTang - 5, 10, 10);
            String coordString = "(" + realX + ", " + realY + ")";
            g.drawString(coordString, xTang + 8, yTang - 5);
            double m = pm.computeSlope(realX);
            double b = realY - m * realX;
            double realX1, realX2, realY1, realY2;
            if(-1 <= m && m <= 1)
            {
                realX1 = ((double) width / (double) 2) / (double) scale;
                realX2 = -realX1;
                realY1 = m * realX1 + b;
                realY2 = m * realX2 + b;
            }
            else
            {
                realY1 = ((double) height / (double) 2) / (double) scale;
                realY2 = -realY1;
                realX1 = (realY1 - b) / m;
                realX2 = (realY2 - b) / m;
            }
            int x1 = width / 2 + (int) (realX1 * scale);
            int x2 = width / 2 + (int) (realX2 * scale);
            int y1 = height / 2 - (int) (realY1 * scale);
            int y2 = height / 2 - (int) (realY2 * scale);
            g.drawLine(x1, y1, x2, y2);
            String s = m + "*x +" + b;
            g.drawString(s, 10, 10);
        }
    }

    /**
     * Methode, die bei einer &Auml;nderung des Modells aufgerufen
     * wird.
     */
    public void polynomChanged()
    {
        repaint();
    }
}

/**
 * Klasse SliderController zur Reaktion auf das Ändern eines Sliders.
 * 
 * @author Rainer Oechsle
 */
class SliderController implements ChangeListener
{
    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Koeffizient, der über diesen Slider verändert werden kann.
     */
    private int i;

    /**
     * Konstruktor.
     */
    public SliderController(PolynomModel pm, int i)
    {
        this.pm = pm;
        this.i = i;
    }

    /**
     * Methode stateChanged der Schnittstelle ChangeListener.
     */
    public void stateChanged(ChangeEvent evt)
    {
        JSlider s = (JSlider) evt.getSource();
        pm.setCoefficient(i, s.getValue());
    }
}

/**
 * Klasse TextFieldController zur Reaktion auf die Eingabe von RETURN
 * in einem Textfeld.
 * 
 * @author Rainer Oechsle
 */
class TextFieldController implements ActionListener
{
    /**
     * Konstante, die den Absolutwert des maximal m&ouml;glichen
     * Koeffizienten angibt.
     */
    private static final double ABS_MAX_COEFF = 10.0;

    /**
     * Modell, dessen Daten dargestellt werden sollen.
     */
    private PolynomModel pm;

    /**
     * Koeffizient, der über dieses Textfeld verändert werden kann.
     */
    private int i;

    /**
     * Konstruktor.
     */
    public TextFieldController(PolynomModel pm, int i)
    {
        this.pm = pm;
        this.i = i;
    }

    /**
     * Methode actionPerformed der Schnittstelle ActionListener.
     */
    public void actionPerformed(ActionEvent evt)
    {
        JTextField tf = (JTextField) evt.getSource();
        try
        {
            double c = Double.parseDouble(tf.getText());
            if(Math.abs(c) <= ABS_MAX_COEFF)
            {
                pm.setCoefficient(i, c);
            }
            else if(c > ABS_MAX_COEFF)
            {
                pm.setCoefficient(i, ABS_MAX_COEFF);
            }
            else
            /* c < -ABS_MAX_COEFF */
            {
                pm.setCoefficient(i, -ABS_MAX_COEFF);
            }
        }
        catch(NumberFormatException e)
        {
            pm.setCoefficient(i, pm.getCoefficient(i));
        }
    }
}

/**
 * Klasse ZoomController zur Reaktion auf die Änderung des
 * Skalierungs-Sliders.
 * 
 * @author Rainer Oechsle
 */
class ZoomController implements ChangeListener
{
    /**
     * Referenz auf CurveView.
     */
    private CurveView cView;

    /**
     * Konstruktor.
     */
    public ZoomController(CurveView cView)
    {
        this.cView = cView;
    }

    /**
     * Methode stateChanged der Schnittstelle ChangeListener.
     */
    public void stateChanged(ChangeEvent evt)
    {
        JSlider s = (JSlider) evt.getSource();
        cView.setScale(s.getValue());
    }
}

/**
 * Klasse MouseController zur Reaktion auf das Betreten und Verlassen
 * der Maus der GraphicView. Beim Betreten der GraphicView mit der
 * Maus wird die Tangente sichtbar, beim Verlassen wird sie
 * unsichtbar.
 * 
 * @author Rainer Oechsle
 */
class MouseController implements MouseListener
{
    /**
     * Referenz auf CurveView.
     */
    private CurveView cView;

    /**
     * Konstruktor.
     */
    public MouseController(CurveView cView)
    {
        this.cView = cView;
    }

    /**
     * Methode moluseEntered der Schnittstelle MouseListener.
     */
    public void mouseEntered(MouseEvent evt)
    {
        cView.setTangVisible(true);
    }

    /**
     * Methode mouseExited der Schnittstelle MouseListener.
     */
    public void mouseExited(MouseEvent evt)
    {
        cView.setTangVisible(false);
    }

    /**
     * Methode mousePressed der Schnittstelle MouseListener. Ohne
     * Wirkung.
     */
    public void mousePressed(MouseEvent evt)
    {
    }

    /**
     * Methode mouseReleased der Schnittstelle MouseListener. Ohne
     * Wirkung.
     */
    public void mouseReleased(MouseEvent evt)
    {
    }

    /**
     * Methode mouseClicked der Schnittstelle MouseListener. Ohne
     * Wirkung.
     */
    public void mouseClicked(MouseEvent evt)
    {
    }
}

/**
 * Klasse MouseMotionController zur Reaktion auf das Bewegen der Maus
 * innerhalb der GraphicView. Beim Bewegen der Maus wird die Tangente
 * entsprechend verändert. Dies ist nur abhängig von der x-Koordinate.
 * 
 * @author Rainer Oechsle
 */
class MouseMotionController implements MouseMotionListener
{
    /**
     * Referenz auf CurveView.
     */
    private CurveView cView;

    /**
     * Konstruktor.
     */
    public MouseMotionController(CurveView cView)
    {
        this.cView = cView;
    }

    /**
     * Methode mouseMoved der Schnittstelle MouseMotionListener.
     */
    public void mouseMoved(MouseEvent evt)
    {
        cView.setXTang(evt.getX());
    }

    /**
     * Methode mouseDragged der Schnittstelle MouseMotionListener.
     */
    public void mouseDragged(MouseEvent evt)
    {
        cView.setXTang(evt.getX());
    }
}

/**
 * Klasse MVCFunctionPanel. Repräsentiert den Inhalt des Frames bzw.
 * des Applets.
 * 
 * @author Rainer Oechsle
 */
class MVCFunctionPanel extends JPanel
{
    /**
     * Konstruktor.
     */
    public MVCFunctionPanel(PolynomModel model)
    {
        setLayout(new BorderLayout());

        FormulaView pfView = new FormulaView(model);
        add(pfView, BorderLayout.NORTH);
        SliderView sView = new SliderView(model);
        add(sView, BorderLayout.WEST);
        CoefficientsView coeff = new CoefficientsView(model);
        add(coeff, BorderLayout.SOUTH);
        CurveView cView = new CurveView(model);
        add(cView, BorderLayout.CENTER);
        JSlider zoomSlider = new JSlider(SwingConstants.VERTICAL,
                                         10, 100, cView.getScale());
        ZoomController zoomContr = new ZoomController(cView);
        zoomSlider.addChangeListener(zoomContr);
        add(zoomSlider, BorderLayout.EAST);
    }
}

/**
 * Klasse MVCFunctionDrawing. Repräsentiert das Applet mit
 * MVCFunctionPanel. Enthält auch main-Methode, in der ein Frame mit
 * einem MVCFunctionPanel erzeugt wird.
 * 
 * @author Rainer Oechsle
 */
public class MVCFunctionDrawing extends JApplet
{
    /**
     * init-Methode.
     */
    public void init()
    {
        PolynomModel model = new PolynomModel(5);
        MVCFunctionPanel p = new MVCFunctionPanel(model);
        add(p);
    }

    /**
     * main-Methode. Erzeugt ExitFrame mit MVCFunctionPanel.
     */
    public static void main(String[] args)
    {
        int degree = 4;
        if(args.length > 0)
        {
            try
            {
                degree = Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Das Argument muss eine ganze Zahl sein.");
                System.exit(0);
            }
            if(degree < 0)
            {
                System.out.println("Das Argument muss eine "
                                   + "positive Zahl sein.");
                System.exit(0);
            }
            if(degree > 10)
            {
                System.out.println("Der Grad ist zu groß. "
                                   + "Das kann ich nicht mehr.");
                System.exit(0);
            }
        }

        PolynomModel model = new PolynomModel(degree);

        MVCFunctionPanel p1 = new MVCFunctionPanel(model);
        JFrame f1 = new JFrame("Polynomdarstellung");
        f1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f1.add(p1);
        f1.setLocation(20, 20);
        f1.setSize(1000, 800);
        f1.setVisible(true);

        MVCFunctionPanel p2 = new MVCFunctionPanel(model);
        JFrame f2 = new JFrame("Polynomdarstellung");
        f2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f2.add(p2);
        f2.setLocation(600, 400);
        f2.setSize(400, 300);
        f2.setVisible(true);
    }
}
