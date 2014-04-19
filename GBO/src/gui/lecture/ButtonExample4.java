package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonExample4 extends JFrame implements ActionListener
{
    private int times;
    private JLabel l;

    public ButtonExample4()
    {
        super("Beispiel für Button");
        times = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton b = new JButton("Drück mich!");
        panel.add(b);
        b.addActionListener(this);
        l = new JLabel();
        panel.add(l);
        add(panel);
        setSize(200, 100);
        //setUndecorated(true);
        //setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        times++;
        l.setText("Der Button wurde " + times + "-mal gedrückt.");
    }

    public static void main(String[] args)
    {
        new ButtonExample4();
        new ButtonExample4();
    }
}
