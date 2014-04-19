package gui.lecture;

import java.awt.event.*;
import javax.swing.*;

public class ButtonExample1 extends JFrame
{
    public ButtonExample1()
    {
        super("Beispiel für Button");
        JButton b = new JButton("Drück mich!");
        add(b);
        MyHandler h = new MyHandler();
        b.addActionListener(h);
        //MyHandler2 h2 = new MyHandler2();
        //b.addActionListener(h2);
        //setLocation(100, 50);
        pack();
        //setSize(270, 90);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ButtonExample1();
    }
}

class MyHandler implements ActionListener
{
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Der Button wurde gedrückt.");
    }
}

class MyHandler2 implements ActionListener
{
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Das sage ich auch.");
    }
}
