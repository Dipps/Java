package gui.lecture;

import javax.swing.*;

class AppletRunner implements Runnable
{
    private JApplet applet;
    
    public AppletRunner(JApplet applet)
    {
        this.applet = applet;
    }

    public void run()
    {
        DrawingAndStoringExample z = new DrawingAndStoringExample();
        applet.add(z);
    }
}

public class DrawingAndStoringApplet2 extends JApplet
{
    public void init()
    {
        AppletRunner appletRunner = new AppletRunner(this);
        try
        {
            SwingUtilities.invokeAndWait(appletRunner);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}