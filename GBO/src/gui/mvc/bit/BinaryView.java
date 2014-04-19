package gui.mvc.bit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BinaryView extends JLabel implements IBitModelListener
{
    private final IBitModel bm;

    public BinaryView(final IBitModel bm)
    {
        super();
        this.bm = bm;
        setText(toBinary());
        this.setHorizontalAlignment(SwingConstants.CENTER);

    }

    @Override
    public void modelChanged()
    {
        setText(toBinary());
    }

    public String toBinary()
    {
        final StringBuilder s = new StringBuilder("");
        for (int i = 0; i < bm.getLength(); i++)
        {
            if (bm.get(i))
            {
                s.append("1");
            }
            else
            {
                s.append("0");
            }
        }
        s.reverse();
        return s.toString();
    }
}
