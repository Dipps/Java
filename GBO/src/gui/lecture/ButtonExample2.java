package gui.lecture;

import java.awt.event.*;
import javax.swing.*;

public class ButtonExample2 extends JFrame implements ActionListener
{
    public ButtonExample2()
    {
        super("Beispiel f�r Button");
        JButton b = new JButton("Dr�ck mich!");
        add(b);
        b.addActionListener(this);
        setLocation(100, 50);
        setSize(270, 90);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Der Button wurde gedr�ckt.");
    }

    public static void main(String[] args)
    {
        new ButtonExample2();
    }
}
