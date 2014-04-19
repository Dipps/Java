package gui.mvc.plusminus;

import javax.swing.JLabel;

public class OctTextView extends JLabel implements IPlusMinusListener
{
    public OctTextView(final int value)
    {
        super(String.format("%o", value) + " (oktal)");
        this.setName("octalLabel");
    }

    @Override
    public void plusMinusModelChanged(final PlusMinusModel model)
    {
        final int value = model.getCounter();
        setText(String.format("%o", value) + " (oktal)");

    }

}
