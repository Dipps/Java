package gui.pizza;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Pizza extends JFrame implements ActionListener
{
    private final JButton bestellen;

    private final JTextArea bestelltext;

    private final String[] groesseArray;

    private final double[] grundPreis;

    private final String[] zutatenArray;

    private final double[] zutatenPreis;

    private final ArrayList<JCheckBox> checkBoxList;

    private final ArrayList<JRadioButton> radioButtonList;

    public Pizza(final String title, final String[] groesse, final double[] grundPreis, final String[] zutaten, final double[] zutatenPreis)
    {
        super(title);
        if (groesse.length != grundPreis.length || zutaten.length != zutatenPreis.length)
        {
            throw new IllegalArgumentException("Arraygroessen der Argumente passen nicht zusammen!");
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.groesseArray = groesse;
        this.grundPreis = grundPreis;
        this.zutatenArray = zutaten;
        this.zutatenPreis = zutatenPreis;
        checkBoxList = new ArrayList<>();
        radioButtonList = new ArrayList<>();

        // Hauptpanel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        // Checkboxes
        final JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(0, 2));
        final JCheckBox kaese = new JCheckBox("K\u00e4se");
        kaese.setName("K\u00e4se");
        kaese.setSelected(true);
        kaese.setEnabled(false);
        checkBoxList.add(kaese);

        final JCheckBox tomaten = new JCheckBox("Tomaten");
        tomaten.setName("Tomaten");
        tomaten.setSelected(true);
        tomaten.setEnabled(false);
        checkBoxList.add(tomaten);

        boxPanel.add(kaese);
        boxPanel.add(tomaten);

        for (final String i : zutaten)
        {
            final JCheckBox cb = new JCheckBox(i);
            cb.setName((i));
            boxPanel.add(cb);
            checkBoxList.add(cb);
        }

        // Radiobuttons
        final JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 0));
        final ButtonGroup rBG = new ButtonGroup();
        for (final String i : groesse)
        {
            final JRadioButton rB = new JRadioButton(i);
            rB.setName(i);
            radioPanel.add(rB);
            rBG.add(rB);
            radioButtonList.add(rB);
        }

        bestellen = new JButton("Bestellen");
        bestellen.setName("bestellen");
        bestellen.addActionListener(this);

        bestelltext = new JTextArea();
        bestelltext.setName("bestelltext");
        bestelltext.setEditable(false);
        bestelltext.setLineWrap(true);
        bestelltext.setWrapStyleWord(true);

        mainPanel.add(boxPanel);
        mainPanel.add(radioPanel);
        mainPanel.add(bestellen);
        mainPanel.add(bestelltext);

        add(mainPanel);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        String groesse = "";
        String zutaten = "";
        double preis = 0.0;

        // Welcher RadioButton ist ausgewählt
        for (final JRadioButton i : radioButtonList)
        {
            if (i.isSelected())
            {
                groesse = i.getText();
                preis = preisBerechnen(groesse, preis, groesseArray, grundPreis);
            }
        }
        // Welche Checkbox ist ausgewählt
        for (final JCheckBox i : checkBoxList)
        {
            if (i.isSelected())
            {
                zutaten += i.getText() + ", "; // StringBuilder verwenden für +=
                                               // bessere performance
                preis = preisBerechnen(i.getText(), preis, zutatenArray, zutatenPreis);
            }
        }
        bestelltext.setText("Sie haben eine Pizza der Größe \"" + groesse + "\" mit " + zutaten + "bestellt. Der Preis beträgt " + preis + " €. Vielen Dank");
    }

    public double preisBerechnen(final String s, double preis, final String[] sA, final double[] pA)
    {
        for (int i = 0; i < sA.length; i++)
        {
            if (s.equals(sA[i]))
            {
                preis += pA[i];
            }
        }

        return preis;
    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final String[] groesse =
        { "klein", "normal", "gro\u00df" };
        final double[] grundPreis =
        { 1.0, 2.0, 3.0 };
        final String[] zutaten =
        { "Artischocken", "Paprika", "Ei", "Spinat", "Knoblauch", "Mais" };
        final double[] zutatenPreis =
        { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };

        new Pizza("Pizza", groesse, grundPreis, zutaten, zutatenPreis);

    }
}
