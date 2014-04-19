package gui.plusminus;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlusMinus extends JFrame
{
    private final JLabel label;

    private final JButton plus;

    private final JButton minus;

    private int wert;

    private final int min;

    private final int max;

    private final int x;

    public PlusMinus(final int min, final int max, final int x)
    {
        super("PlusMinus");
        this.min = min;
        this.max = max;
        this.x = x;
        wert = min;

        plus = new JButton("+", new ImageIcon("res/plus.jpg"));
        plus.setName("plus");
        plus.setToolTipText("plus " + x);
        add(plus);

        // -------------------------------------------
        // -------------- Variante 1 -----------------
        // final Handler handler = new Handler(this);
        // plus.addActionListener(handler);
        // -------------------------------------------

        // -------------------------------------------
        // -------------- Variante 2 -----------------
        final Handler2 handler1 = new Handler2(this, true);
        plus.addActionListener(handler1);
        // -------------------------------------------

        // -------------------------------------------
        // -------------- Variante 3 -----------------
        // final PlusHandler pH = new PlusHandler(this);
        // plus.addActionListener(pH);
        // -------------------------------------------

        if (wert + x > max)
        {
            plus.setEnabled(false);
        }

        label = new JLabel("" + wert);
        label.setName("label");
        add(label);
        // label.setHorizontalAlignment(JLabel.CENTER);

        minus = new JButton("-", new ImageIcon("res/minus.jpg"));
        minus.setName("minus");
        minus.setToolTipText("Minus " + x);
        add(minus);

        // -------------------------------------------
        // -------------- Variante 1 -----------------
        // minus.addActionListener(handler);
        // -------------------------------------------

        // -------------------------------------------
        // -------------- Variante 2 -----------------
        final Handler2 handler2 = new Handler2(this, false);
        minus.addActionListener(handler2);
        // -------------------------------------------

        // -------------------------------------------
        // -------------- Variante 3 -----------------
        // final MinusHandler mH = new MinusHandler(this);
        // minus.addActionListener(mH);
        // -------------------------------------------

        minus.setEnabled(false);

        setLayout(new GridLayout(3, 0));
        pack();
        setVisible(true);
    }

    public void increment()
    {
        wert += x;
        label.setText("" + wert);
        minus.setEnabled(true);

        if ((wert + x) > max)
        {
            plus.setEnabled(false);
        }

    }

    public void decrement()
    {
        wert -= x;
        label.setText("" + wert);
        plus.setEnabled(true);

        if ((wert - x) < min)
        {
            minus.setEnabled(false);
        }
    }

    public static void main(final String[] args)
    {
        new PlusMinus(10, 19, 3);
    }

}
