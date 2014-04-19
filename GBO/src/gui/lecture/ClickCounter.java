package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ClickCounterPanel extends JPanel implements ActionListener
{
    private int times;
    private JLabel l;

    public ClickCounterPanel()
    {
        setLayout(new GridLayout(0, 1));
        JButton b = new JButton("Drück mich!");
        add(b);
        b.addActionListener(this);
        l = new JLabel();
        add(l);
    }

    public void actionPerformed(ActionEvent evt)
    {
        times++;
        l.setText("Der Button wurde " + times + "-mal gedrückt.");
    }
}

public class ClickCounter extends JApplet
{
    public void init()
    {
        add(new ClickCounterPanel());
        showStatus("Hallo, wie geht's?");
    }
    
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Beispiel für Button");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(new ClickCounterPanel());
        f.setLocation(100, 50);
        f.setSize(300, 100);
        f.setVisible(true);
    }
}
