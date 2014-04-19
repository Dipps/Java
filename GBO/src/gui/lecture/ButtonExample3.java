package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonExample3 extends JFrame implements ActionListener
{
    private int times;
    private JLabel l;

    public ButtonExample3()
    {
        super("Beispiel f�r Button");
        setLayout(new GridLayout(0, 1));
        JButton b = new JButton("Dr�ck mich!");
        add(b);
        b.addActionListener(this);
        l = new JLabel();
        add(l);
        setSize(200, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        times++;
        l.setText("Der Button wurde " + times + "-mal gedr�ckt.");
    }

    public static void main(String[] args)
    {
        new ButtonExample3();
        new ButtonExample3();
    }
}
