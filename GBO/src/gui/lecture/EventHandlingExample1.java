package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventHandlingExample1 extends JFrame
{
    public EventHandlingExample1(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton b1 = new JButton("Knopf 1");
        JButton b2 = new JButton("Knopf 2");
        JLabel l = new JLabel();
        ClickHandler1 handler = new ClickHandler1(l);
        b1.addActionListener(handler);
        b2.addActionListener(handler);
        panel.add(b1);
        panel.add(b2);
        panel.add(l);
        add(panel);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new EventHandlingExample1("Beispiel für " + "Ereignisbehandlung");
    }
}

class ClickHandler1 implements ActionListener
{
    private JLabel label;

    public ClickHandler1(JLabel label)
    {
        this.label = label;
    }

    public void actionPerformed(ActionEvent evt)
    {
        String s = evt.getActionCommand();
        if(s.equals("Knopf 1"))
        {
            label.setText("Sie haben den ersten Knopf gedrückt.");
            label.setForeground(Color.GREEN);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        else
        {
            label.setText("Sie haben den zweiten Knopf gedrückt.");
            label.setForeground(Color.RED);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }
}
