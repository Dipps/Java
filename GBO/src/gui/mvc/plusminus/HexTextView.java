package gui.mvc.plusminus;

import javax.swing.JLabel;

public class HexTextView extends JLabel implements IPlusMinusListener
{
    public HexTextView(final int value)
    {
        super(String.format("%x", value) + " (hexadezimal)");
        this.setName("hexadecimalLabel");
    }

    @Override
    public void plusMinusModelChanged(final PlusMinusModel model)
    {
        final int value = model.getCounter();
        setText(String.format("%x", value) + " (hexadezimal)");

    }

}
