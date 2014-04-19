package gui.mvc.bit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DecimalView extends JLabel implements IBitModelListener
{
    private final IBitModel bm;

    public DecimalView(final IBitModel bm)
    {
        super();
        this.bm = bm;
        setText("" + toDecimal());
        this.setHorizontalAlignment(SwingConstants.CENTER);

    }

    @Override
    public void modelChanged()
    {
        setText("" + toDecimal());

    }

    public int toDecimal()
    {
        int value = 0;
        for (int i = 0; i < bm.getLength(); i++)
        {
            if (bm.get(i))
            {
                value += Math.pow(2, i);
            }
        }

        return value;
    }

}
