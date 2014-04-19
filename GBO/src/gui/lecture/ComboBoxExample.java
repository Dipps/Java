package gui.lecture;

import java.awt.event.*;
import javax.swing.*;

public class ComboBoxExample extends JFrame implements ActionListener
{
    public ComboBoxExample(String title, String[] cbText)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JComboBox<String> cb = new JComboBox<>(cbText);
        cb.setEditable(true);
        cb.addActionListener(this);
        add(cb);
        setLocation(200, 200);
        setSize(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        JComboBox<?> cb = (JComboBox<?>) evt.getSource();
        System.out.println("ActionEvent: selected " + cb.getSelectedItem()
                           + " (Index " + cb.getSelectedIndex() + ")");
    }

    public static void main(String[] args)
    {
        new ComboBoxExample("Beispiel für ComboBox", args);
    }
}
