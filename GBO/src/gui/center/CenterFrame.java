package gui.center;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class CenterFrame extends JFrame implements WindowListener
{
    public CenterFrame(final String title)
    {
        super(title);
        addWindowListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public void setIntoCenter()
    {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        final int xPos = d.width / 2 - getWidth() / 2;
        final int yPos = d.height / 2 - getHeight() / 2;

        setLocation(xPos, yPos);
    }

    public static void setAllIntoCenter()
    {
        // ForEach
        for (final Frame currentFrame : Frame.getFrames())
        {
            if (currentFrame instanceof CenterFrame)
            {
                ((CenterFrame) currentFrame).setIntoCenter();
            }
        }

        /*
         * Frame[] f = Frame.getFrames(); for (int i = 0; i < f.length; i++) {
         * if (f[i] instanceof CenterFrame) { ((CenterFrame)
         * f[i]).setIntoCenter(); } }
         */
    }

    public static void setAllVisible(final boolean b)
    {
        for (final Frame currentFrame : Frame.getFrames())
        {
            if (currentFrame instanceof CenterFrame)
            {
                currentFrame.setVisible(b);
            }
        }

    }

    @Override
    public void windowOpened(final WindowEvent e)
    {
        System.out.println("Methode: windowOpened " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowClosing(final WindowEvent e)
    {
        System.out.println("Methode: windowClosing " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowClosed(final WindowEvent e)
    {
        System.out.println("Methode: windowClosed " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowIconified(final WindowEvent e)
    {
        System.out.println("Methode: windowIconified " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowDeiconified(final WindowEvent e)
    {
        System.out.println("Methode: windowDeiconified " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowActivated(final WindowEvent e)
    {
        System.out.println("Methode: windowActivated " + "Fenster: " + this.getTitle());

    }

    @Override
    public void windowDeactivated(final WindowEvent e)
    {
        System.out.println("Methode: windowDeactivated " + "Fenster: " + this.getTitle());

    }

    public static void main(final String[] args)
    {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        System.out.println("Hoehe: " + d.getHeight() + "\nBreite: " + d.getWidth());
        final CenterFrame t1 = new CenterFrame("CenterFrame1");
        t1.setLocation(10, 30);
        final CenterFrame t2 = new CenterFrame("CenterFrame2");
        t2.setLocation(100, 300);
        final CenterFrame t3 = new CenterFrame("CenterFrame3");
        t3.setIntoCenter();
        final CenterFrame t4 = new CenterFrame("CenterFrame4");
        t4.setLocation(800, 900);
        final CenterFrame t5 = new CenterFrame("CenterFrame5");
        t5.setLocation(0, 0);
        new ControlFrame();
    }
}
