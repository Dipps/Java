package gui.lecture;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BorderExample extends JFrame
{
    public BorderExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 0));

        JPanel first = new JPanel(new GridLayout(0, 1));
        first.add(new JButton("Button 1"));
        first.add(new JLabel("Label 1"));
        first.add(new JTextArea("Text 1"));
        Border lineBorder1 = BorderFactory.createLineBorder(Color.BLACK);
        Border titleBorder1 = BorderFactory.createTitledBorder(lineBorder1,
                                                               "Links");
        first.setBorder(titleBorder1);
        add(first);

        JPanel second = new JPanel(new GridLayout(0, 1));
        second.add(new JButton("Button 2"));
        second.add(new JLabel("Label 2"));
        second.add(new JTextArea("Text 2"));
        Border lineBorder2 = BorderFactory.createLineBorder(Color.BLACK);
        Border titleBorder2 = BorderFactory.createTitledBorder(lineBorder2,
                                                               "Rechts");
        second.setBorder(titleBorder2);
        add(second);

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BorderExample("Beispiel für Rahmen");
    }
}