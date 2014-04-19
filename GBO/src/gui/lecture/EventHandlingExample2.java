package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventHandlingExample2 extends JFrame
{
    private JLabel l;

    public EventHandlingExample2(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton b1 = new JButton("Knopf 1");
        JButton b2 = new JButton("Knopf 2");
        l = new JLabel();
        ClickHandler2 handler1 = new ClickHandler2(l,
                                                   "Sie haben den ersten Knopf gedrückt.",
                                                   Color.GREEN,
                                                   SwingConstants.CENTER);
        b1.addActionListener(handler1);
        ClickHandler2 handler2 = new ClickHandler2(l,
                                                   "Sie haben den zweiten Knopf gedrückt.",
                                                   Color.RED,
                                                   SwingConstants.RIGHT);
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
        new EventHandlingExample2("Beispiel für " + "Ereignisbehandlung");
    }
}

class ClickHandler2 implements ActionListener
{
    private JLabel label;
    private String text;
    private Color color;
    private int alignment;

    public ClickHandler2(JLabel label, String text,
                         Color color, int alignment)
    {
        this.label = label;
        this.text = text;
        this.color = color;
        this.alignment = alignment;
    }

    public void actionPerformed(ActionEvent evt)
    {
        label.setText(text);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);
    }
}
