package gui.lecture;

import java.awt.event.*;
import javax.swing.*;

public class TextFieldExample extends JFrame implements ActionListener
{
    public TextFieldExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextField text = new JTextField();
        text.addActionListener(this);
        add(text);
        setSize(300, 80);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        JTextField tf = (JTextField) evt.getSource();
        String input = tf.getText();
        System.out.println("Eingabe war: '" + input + "'");
        tf.setText("");
    }

    public static void main(String[] args)
    {
        new TextFieldExample("Beispiel für JTextField");
    }
}
