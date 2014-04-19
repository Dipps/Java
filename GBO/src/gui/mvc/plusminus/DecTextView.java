package gui.mvc.plusminus;

import javax.swing.JLabel;

public class DecTextView extends JLabel implements IPlusMinusListener
{

    public DecTextView(final int value)
    {
        super(value + " (dezimal)");
        this.setName("decimalLabel");
    }

    @Override
    public void plusMinusModelChanged(final PlusMinusModel model)
    {
        setText(model.getCounter() + " (dezimal)");

    }
}
