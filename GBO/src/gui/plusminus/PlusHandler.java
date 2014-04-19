package gui.plusminus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusHandler implements ActionListener
{
    private PlusMinus pm;
    
    public PlusHandler(PlusMinus pm)
    {
        this.pm = pm;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        pm.increment();
    }

}
