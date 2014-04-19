package gui.mvc.plusminus;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MinusButtonView extends JPanel implements IPlusMinusListener
{
    private final JButton b;

    public MinusButtonView(final JButton b, final PlusMinusModel model)
    {
        this.b = b;
        b.setName("minus");
        if (!model.canDecrement())
        {
            b.setEnabled(false);
        }
        setLayout(new BorderLayout());
        add(b);

    }

    @Override
    public void plusMinusModelChanged(final PlusMinusModel model)
    {
        if (model.canDecrement())
        {
            b.setEnabled(true);
        }
        else
        {
            b.setEnabled(false);
        }
    }

}
