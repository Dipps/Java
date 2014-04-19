package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextAreaExample extends JFrame implements ActionListener
{
    private JTextArea ta;

    public TextAreaExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);
        add(scrollPane, BorderLayout.CENTER);
        String[] keywords = {"class", "extends", "import",
                             "interface", "synchronized"};
        JComboBox<String> c = new JComboBox<String>(keywords);
        c.addActionListener(this);
        add(c, BorderLayout.SOUTH);
        setSize(600, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        JComboBox<?> cb = (JComboBox<?>) evt.getSource();
        String s = (String) cb.getSelectedItem();
        ta.insert(s, ta.getSelectionStart());
        //ta.replaceSelection(s);
    }

    public static void main(String[] args)
    {
        new TextAreaExample("Beispiel für TextArea");
    }
}