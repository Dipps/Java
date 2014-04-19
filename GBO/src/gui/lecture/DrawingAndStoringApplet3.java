package gui.lecture;

import javax.swing.*;

public class DrawingAndStoringApplet3 extends JApplet
{
    public void init()
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    DrawingAndStoringExample z = new DrawingAndStoringExample();
                    add(z);
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
