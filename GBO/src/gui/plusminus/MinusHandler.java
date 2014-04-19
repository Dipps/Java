package gui.plusminus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinusHandler implements ActionListener
{
    private PlusMinus pm;
    
    public MinusHandler(PlusMinus pm)
    {
        this.pm = pm;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        pm.decrement();
    }

}
