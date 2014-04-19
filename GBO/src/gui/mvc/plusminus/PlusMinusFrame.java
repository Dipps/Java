package gui.mvc.plusminus;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * GBO-Komponente
 */
public class PlusMinusFrame extends JFrame
{

    /**
     * Initialisiert ein neues PlusMinusFrame-Objekt mit dem uebergebenen
     * PlusMinusModel.
     * 
     * @param model
     *            Modell
     */
    public PlusMinusFrame(final PlusMinusModel model)
    {
        super("PlusMinus");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final JPanel panel = new JPanel(new GridLayout(0, 1));

        final PlusMinusController controller = new PlusMinusController(model);

        final JButton plus = new JButton("+");
        plus.addActionListener(controller);
        final PlusButtonView plusButton = new PlusButtonView(plus, model);
        model.addPlusMinusModelListener(plusButton);
        panel.add(plusButton);

        final DecTextView decText = new DecTextView(model.getCounter());
        model.addPlusMinusModelListener(decText);
        panel.add(decText);

        final HexTextView hexText = new HexTextView(model.getCounter());
        model.addPlusMinusModelListener(hexText);
        panel.add(hexText);

        final OctTextView octText = new OctTextView(model.getCounter());
        model.addPlusMinusModelListener(octText);
        panel.add(octText);

        final JButton minus = new JButton("-");
        minus.addActionListener(controller);
        final MinusButtonView minusButton = new MinusButtonView(minus, model);
        model.addPlusMinusModelListener(minusButton);
        panel.add(minusButton);

        add(panel);
        pack();
        setVisible(true);
    }

    public static void main(final String[] args)
    {
        final PlusMinusModel model = new PlusMinusModel(0, 20, 3);
        new PlusMinusFrame(model);
        new PlusMinusFrame(model);
        new PlusMinusFrame(model);
        new PlusMinusFrame(model);

    }
}
