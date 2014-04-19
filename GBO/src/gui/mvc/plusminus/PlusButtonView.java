package gui.mvc.plusminus;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PlusButtonView extends JPanel implements IPlusMinusListener
{
    private final JButton b;

    public PlusButtonView(final JButton b, final PlusMinusModel model)
    {
        this.b = b;
        b.setName("plus");
        if (!model.canIncrement())
        {
            b.setEnabled(false);
        }
        setLayout(new BorderLayout());
        add(b);
    }

    @Override
    public void plusMinusModelChanged(final PlusMinusModel model)
    {
        if (model.canIncrement())
        {
            b.setEnabled(true);
        }
        else
        {
            b.setEnabled(false);
        }

    }

}
