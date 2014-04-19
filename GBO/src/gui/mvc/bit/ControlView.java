package gui.mvc.bit;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ControlView extends JPanel implements IBitModelListener
{

    private final IBitModel bm;

    private final JCheckBox[] cbList;

    public ControlView(final IBitModel bm)
    {
        this.bm = bm;
        cbList = new JCheckBox[bm.getLength()];

        // Bitmodel von hinten nach vorn durchlaufen,
        // um größtes Element am Anfang zu haben
        for (int i = bm.getLength() - 1; i >= 0; i--)
        {
            final JCheckBox cb = new JCheckBox("" + i);
            cb.setName("" + i);
            add(cb);
            cb.addItemListener(new BitViewController(this.bm, i));
            cbList[i] = cb;
        }
        setLayout(new GridLayout(1, 0));

    }

    @Override
    public void modelChanged()
    {
        for (int i = 0; i < cbList.length; i++)
        {
            if (bm.get(i))
            {
                cbList[i].setSelected(true);
            }
            else
            {
                cbList[i].setSelected(false);
            }
        }

    }
}
