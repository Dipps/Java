package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonExample7 extends JFrame implements ActionListener
{
    private int times;
    private JLabel l;

    public ButtonExample7()
    {
        super("Beispiel für HTML-Text in Buttons und Labels");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        JButton b = new JButton("<html><font color=red>"
                                + "Drück<br><bold>mich</bold>!"
                                + "</font></html>");
        add(b);
        b.addActionListener(this);
        l = new JLabel();
        add(l);
        setSize(600, 160);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        times++;
        l.setText("<html>Der Button wurde " + "<font size=+2>" + times
                  + "</font>-mal gedrückt.</html>");
    }

    public static void main(String[] args)
    {
        new ButtonExample7();
    }
}
