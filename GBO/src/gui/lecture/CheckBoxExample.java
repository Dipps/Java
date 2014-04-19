package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxExample extends JFrame implements ItemListener,
                                           ActionListener
{
    public CheckBoxExample(String title, String[] cbText)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel p = new JPanel(new GridLayout(3, 0));
        for(int i = 0; i < cbText.length; i++)
        {
            JCheckBox cb = new JCheckBox(cbText[i]);
            p.add(cb);
            cb.addItemListener(this);
            cb.addActionListener(this);
        }
        add(p);
        setSize(800, 150);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent evt)
    {
        JCheckBox cb = (JCheckBox) evt.getItemSelectable();
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            System.out.println("ItemEvent: selected " + cb.getText());
        }
        else if(evt.getStateChange() == ItemEvent.DESELECTED)
        {
            System.out.println("ItemEvent: deselected " + cb.getText());
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        JCheckBox cb = (JCheckBox) evt.getSource();
        if(cb.isSelected())
        {
            System.out.println("ActionEvent: selected " + cb.getText());
        }
        else
        {
            System.out.println("ActionEvent: deselected " + cb.getText());
        }
    }

    public static void main(String[] args)
    {
        new CheckBoxExample("Beispiel für CheckBox", args);
    }
}
