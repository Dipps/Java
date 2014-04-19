package gui.lecture;

import java.awt.*; // Layouts
import javax.swing.*;

public class LayoutExample extends JFrame
{
    private void helpAndExit()
    {
        System.out.println("first argument must be: flow "
                           + "OR grid OR border OR boxx OR boxy "
                           + "OR boxline OR boxpage");
        System.exit(0);
    }

    public LayoutExample(String[] text)
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel p = new JPanel();

        if(text.length == 0)
        {
            helpAndExit();
        }
        if(text[0].equals("flow"))
        {
            setTitle("Beispiel für FlowLayout");
            p.setLayout(new FlowLayout());
        }
        else if(text[0].equals("grid"))
        {
            setTitle("Beispiel für GridLayout");
            p.setLayout(new GridLayout(3, 0));
        }
        else if(text[0].equals("border"))
        {
            setTitle("Beispiel für BorderLayout");
            p.setLayout(new BorderLayout());
        }
        else if(text[0].equals("boxx"))
        {
            setTitle("Beispiel für BoxLayout(X_AXIS)");
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        }
        else if(text[0].equals("boxy"))
        {
            setTitle("Beispiel für BoxLayout(Y_AXIS)");
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        }
        else if(text[0].equals("boxline"))
        {
            setTitle("Beispiel für BoxLayout(LINE_AXIS)");
            p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        }
        else if(text[0].equals("boxpage"))
        {
            setTitle("Beispiel für BoxLayout(PAGE_AXIS)");
            p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        }
        else
        {
            helpAndExit();
        }

        if(text[0].equals("border"))
        {
            if(text.length > 1)
            {
                p.add(new JButton(text[1]), BorderLayout.NORTH);
            }
            if(text.length > 2)
            {
                p.add(new JButton(text[2]), BorderLayout.WEST);
            }
            if(text.length > 3)
            {
                p.add(new JButton(text[3]), BorderLayout.SOUTH);
            }
            if(text.length > 4)
            {
                p.add(new JButton(text[4]), BorderLayout.EAST);
            }
            if(text.length > 5)
            {
                p.add(new JButton(text[5]), BorderLayout.CENTER);
            }
        }
        else
        {
            for(int i = 1; i < text.length; i++)
            {
                p.add(new JButton(text[i]));
            }
        }
        add(p);
        setLocation(100, 100);
        setSize(400, 250);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new LayoutExample(args);
    }
}
