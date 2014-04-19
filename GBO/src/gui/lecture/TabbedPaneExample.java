package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class TabbedPaneExample extends JFrame
{
    public TabbedPaneExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel buttons1 = new JPanel(new GridLayout(0, 1));
        buttons1.add(new JButton("1"));
        buttons1.add(new JButton("2"));
        buttons1.add(new JButton("3"));

        JPanel buttons2 = new JPanel(new GridLayout(1, 0));
        buttons2.add(new JButton("a"));
        buttons2.add(new JButton("b"));
        buttons2.add(new JButton("c"));
        buttons2.add(new JButton("d"));

        JTextArea area = new JTextArea("hallo");
        JScrollPane scrollPane = new JScrollPane(area);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Buttons (1 Spalte)", buttons1);
        tp.addTab("Buttons (1 Zeile)", buttons2);
        tp.addTab("Texteingabe", scrollPane);

        add(tp);
    }

    public static void main(String[] args)
    {
        TabbedPaneExample frame = new TabbedPaneExample("Beispiel für "
                                                        + "Tabbed Panes");

        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
