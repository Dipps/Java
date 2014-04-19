package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonExample extends JFrame implements ItemListener,
                                              ActionListener
{
    public RadioButtonExample(String title, String[] rbText)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(3, 0));
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < rbText.length; i++)
        {
            JRadioButton rb = new JRadioButton(rbText[i]);
            panel.add(rb);
            bg.add(rb);
            rb.addItemListener(this);
            rb.addActionListener(this);
        }
        add(panel);
        setSize(800, 150);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent evt)
    {
        JRadioButton rb = (JRadioButton) evt.getItemSelectable();
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            System.out.println("ItemEvent: selected " + rb.getText());
        }
        else if(evt.getStateChange() == ItemEvent.DESELECTED)
        {
            System.out.println("ItemEvent: deselected " + rb.getText());
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        JRadioButton rb = (JRadioButton) evt.getSource();
        if(rb.isSelected())
        {
            System.out.println("ActionEvent: selected " + rb.getText());
        }
        else
        {
            System.out.println("ActionEvent: deselected " + rb.getText());
        }
    }

    public static void main(String[] args)
    {
        new RadioButtonExample("Beispiel für RadioButton", args);
    }
}
