package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class TextAndPasswordFieldExample extends JFrame implements ActionListener
{
    private JLabel label;

    public TextAndPasswordFieldExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        JTextField text = new JTextField();
        text.addActionListener(this);
        add(text);
        JTextField pw = new JPasswordField();
        pw.addActionListener(this);
        add(pw);
        label = new JLabel();
        add(label);
        setSize(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        /*
        JTextField tf = (JTextField) evt.getSource();
        String textInput = tf.getText();
        label.setText("Eingabe: '" + textInput + "'");
        */    
        JTextField tf = (JTextField) evt.getSource();
        if (tf instanceof JPasswordField)
        {
            JPasswordField pwf = (JPasswordField) tf;
            char[] pwArray = pwf.getPassword();
            /* nur zur Demo (String will man gerade vermeiden) */
            String password = new String(pwArray);
            label.setText("eingegebenes Passwort: '" + password + "'");
            /* Ende Demo */
            /* im Folgenden Annahme: Passwort steht nur
               in pwArray und in JPasswordField (nicht
               in String
            */
            Arrays.fill(pwArray, 'x');
        }
        else
        {
            String text = tf.getText();
            label.setText("eingegebener Text: '" + text + "'");
        }
        tf.setText("");
    }

    public static void main(String[] args)
    {
        new TextAndPasswordFieldExample("Beispiel für JTextField " + "und JPasswordField");
    }
}
