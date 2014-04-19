package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventHandlingExample3 extends JFrame
{
    public EventHandlingExample3(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton b1 = new JButton("Knopf 1");
        JButton b2 = new JButton("Knopf 2");
        JLabel l = new JLabel();
        ClickHandlerButton31 handler1 = new ClickHandlerButton31(l);
        b1.addActionListener(handler1);
        ClickHandlerButton32 handler2 = new ClickHandlerButton32(l);
        b2.addActionListener(handler2);
        panel.add(b1);
        panel.add(b2);
        panel.add(l);
        add(panel);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new EventHandlingExample3("Beispiel für " + "Ereignisbehandlung");
    }
}

class ClickHandlerButton31 implements ActionListener
{
    private JLabel label;

    public ClickHandlerButton31(JLabel label)
    {
        this.label = label;
    }

    public void actionPerformed(ActionEvent evt)
    {
        label.setText("Sie haben den ersten Knopf gedrückt.");
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
}

class ClickHandlerButton32 implements ActionListener
{
    private JLabel label;

    public ClickHandlerButton32(JLabel label)
    {
        this.label = label;
    }

    public void actionPerformed(ActionEvent evt)
    {
        label.setText("Sie haben den zweiten Knopf gedrückt.");
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
