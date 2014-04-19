package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogExample extends JFrame implements ActionListener
{
    private JLabel l;

    public DialogExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton b;

        JPanel column1 = new JPanel();
        b = new JButton("Einfache Nachricht");
        b.addActionListener(this);
        column1.add(b);
        b = new JButton("Information");
        b.addActionListener(this);
        column1.add(b);
        b = new JButton("Warnung");
        b.addActionListener(this);
        column1.add(b);
        b = new JButton("Fehlermeldung");
        b.addActionListener(this);
        column1.add(b);

        JPanel column2 = new JPanel();
        b = new JButton("Ja-Nein-Auswahl");
        b.addActionListener(this);
        column2.add(b);
        b = new JButton("Ja-Nein-Abbr.-Auswahl");
        b.addActionListener(this);
        column2.add(b);

        JPanel column3 = new JPanel();
        b = new JButton("Optionen");
        b.addActionListener(this);
        column3.add(b);

        JPanel column4 = new JPanel();
        b = new JButton("Eingabe");
        b.addActionListener(this);
        column4.add(b);

        JPanel allButtons = new JPanel(new GridLayout(1, 0));
        allButtons.add(column1);
        allButtons.add(column2);
        allButtons.add(column3);
        allButtons.add(column4);

        JPanel all = new JPanel(new BorderLayout());
        all.add(new JLabel("Wählen Sie einen Dialog:"), BorderLayout.NORTH);
        all.add(allButtons, BorderLayout.CENTER);
        l = new JLabel();
        all.add(l, BorderLayout.SOUTH);

        add(all);
        setSize(700, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        String s = evt.getActionCommand();
        l.setText(s);

        if(s.equals("Einfache Nachricht"))
        {
            JOptionPane.showMessageDialog(this,
                                          "Die Vorlesung dauert ja ewig!", s,
                                          JOptionPane.PLAIN_MESSAGE);
        }
        else if(s.equals("Information"))
        {
            JOptionPane.showMessageDialog(this,
                                          "Zu Ihrer Information: Die Vorlesung dauert ewig!",
                                          s, JOptionPane.INFORMATION_MESSAGE);
        }
        else if(s.equals("Warnung"))
        {
            JOptionPane.showMessageDialog(this,
                                          "Vorsicht: Die Vorlesung dauert ewig!",
                                          s, JOptionPane.WARNING_MESSAGE);
        }
        else if(s.equals("Fehlermeldung"))
        {
            JOptionPane.showMessageDialog(this,
                                          "Fehler: Die Vorlesung dauert ewig!",
                                          s, JOptionPane.ERROR_MESSAGE);
        }
        else if(s.equals("Ja-Nein-Auswahl"))
        {
            int rc = JOptionPane.showConfirmDialog(this,
                                                   "Dauert diese Vorlesung ewig?",
                                                   s, JOptionPane.YES_NO_OPTION);
            l.setText("rc = " + rc);
        }
        else if(s.equals("Ja-Nein-Abbr.-Auswahl"))
        {
            int rc = JOptionPane.showConfirmDialog(this,
                                                   "Dauert diese Vorlesung ewig?",
                                                   s,
                                                   JOptionPane.YES_NO_CANCEL_OPTION);
            l.setText("rc = " + rc);
        }
        else if(s.equals("Optionen"))
        {
            Object[] options = {"Sollte nie beginnen",
                                "Hätte vor 5 Minuten aus sein sollen",
                                "Sollte JETZT SOFORT enden",
                                "Sollte wie vorgesehen enden",
                                "Sollte ewig dauern"};
            int rc = JOptionPane.showOptionDialog(this,
                                                  "Was denken Sie über den Endezeitpunkt der Vorlesung?",
                                                  s,
                                                  JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null, options, options[2]);
            l.setText("rc = " + rc);
        }
        else if(s.equals("Eingabe"))
        {
            String input = JOptionPane.showInputDialog(this,
                                                       "Geben Sie den gewünschten Endezeitpunkt der Vorlesung an:",
                                                       s,
                                                       JOptionPane.QUESTION_MESSAGE);
            l.setText("Eingabe war: " + input);
        }
    }

    public static void main(String[] args)
    {
        new DialogExample("Dialog-Beispiel");
    }
}
