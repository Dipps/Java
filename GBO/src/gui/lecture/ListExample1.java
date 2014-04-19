package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListExample1 extends JFrame implements ActionListener
{
    private JList<String> list;
    private String[] selections;

    public ListExample1(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String[] data = {"eins", "zwei", "drei", "vier", "fünf", "sechs",
                         "sieben", "acht", "neun"};
        list = new JList<String>(data);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        ButtonGroup bg = new ButtonGroup();
        selections = new String[3];
        selections[0] = "Single Selection";
        selections[1] = "Single Interval Selection";
        selections[2] = "Multiple Interval Selection";
        for(int i = 0; i < selections.length; i++)
        {
            JRadioButton rb = new JRadioButton(selections[i]);
            if(i == 0)
            {
                rb.setSelected(true);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }
            buttonPanel.add(rb);
            bg.add(rb);
            rb.addActionListener(this);
        }

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(list, BorderLayout.CENTER);
        p.add(buttonPanel, BorderLayout.SOUTH);
        add(p);
        setLocation(200, 200);
        setSize(200, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        if(command.equals(selections[0]))
        {
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        else if(command.equals(selections[1]))
        {
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        }
        else
        {
            // if(command.equals(selections[2]))
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
    }

    public static void main(String[] args)
    {
        new ListExample1("Beispiel für Selektionsmodus");
    }
}
