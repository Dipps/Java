package gui.lecture;

import java.awt.event.*;

public class ButtonExample5 extends ButtonExample4 implements WindowListener
{
    public ButtonExample5()
    {
        addWindowListener(this);
    }

    public void windowClosing(WindowEvent evt)
    {
        System.exit(0);
    }

    public void windowActivated(WindowEvent evt)
    {
    }

    public void windowClosed(WindowEvent evt)
    {
    }

    public void windowDeactivated(WindowEvent evt)
    {
    }

    public void windowDeiconified(WindowEvent evt)
    {
    }

    public void windowIconified(WindowEvent evt)
    {
    }

    public void windowOpened(WindowEvent evt)
    {
    }

    public static void main(String[] args)
    {
        new ButtonExample5();
    }
}
